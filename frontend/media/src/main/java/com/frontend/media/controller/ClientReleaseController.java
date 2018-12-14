package com.frontend.media.controller;


import com.common.base.constant.Constants;
import com.common.base.constant.ReturnBean;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.common.base.util.SpageUtil;
import com.frontend.media.controller.dto.ReleaseDto;
import com.frontend.media.entity.ClientListen;
import com.frontend.media.entity.ClientRelease;
import com.frontend.media.entity.ResContent;
import com.frontend.media.entity.SetField;
import com.frontend.media.service.*;
import com.frontend.media.util.AliyunOssUtil;
import com.frontend.media.util.FileUtil;
import com.frontend.media.util.GsonUtil;
import com.google.common.collect.Maps;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 注册头条号的资源发布者 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-07-16
 */
@Controller
@RequestMapping("/clientRelease")
@Transactional(rollbackFor = Exception.class)
public class ClientReleaseController {

    @Value("${file.server}")
    private String fileServer;

    @Autowired
    private ClientReleaseService releaseService;

    @Autowired
    private SetFieldService fieldService;

    @Autowired
    private ResContentService contentService;

    @Autowired
    private ClientListenService listenService;

    @Autowired
    private FileService fileService;

    @GetMapping("")
    public String index(@RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "rows", defaultValue = "14") int rows,
                        Model model) {
        SpageUtil<ClientRelease> spageUtil = new SpageUtil<>(page, rows);
        spageUtil.setSort("submit_time desc");
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(1);
        spageUtil = releaseService.getUserReleases(spageUtil, params);
        List<ClientRelease> releases = spageUtil.getRows();
        List<ReleaseDto> releaseDtoList = new ArrayList<>();
        if (releases != null && releases.size() > 0) {
            releaseDtoList = releases.stream().map(release -> releaseService.wrapperDto(release)).collect(Collectors
                    .toList());
        }
        model.addAttribute("userReleases", releaseDtoList);
        model.addAttribute("totalUserRelease", spageUtil.getTotal());
        return "resContent/index";
    }

    @GetMapping("/list")
    @ResponseBody
    public String list(@RequestParam(value = "page", defaultValue = "1") int page,
                       @RequestParam(value = "rows", defaultValue = "14") int rows,
                       @RequestParam(value = "name", required = false) String name) {
        ReturnBean returnBean = new ReturnBean();
        SpageUtil<ClientRelease> spageUtil = new SpageUtil<>(page, rows);
        spageUtil.setSort("submit_time desc");
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
        if (StringUtils.isNotEmpty(name)) {
            params.put("name", name);
        }
        spageUtil = releaseService.getUserReleases(spageUtil, params);
        List<ClientRelease> releases = spageUtil.getRows();
        long total = spageUtil.getTotal();
        List<ReleaseDto> releaseDtoList = new ArrayList<>();
        if (releases != null && releases.size() > 0) {
            releaseDtoList = releases.stream().map(release -> releaseService.wrapperDto(release)).collect(Collectors
                    .toList());
        }
        returnBean.setData(releaseDtoList);
        returnBean.setCount(total);
        return returnBean.toJsonString();
    }

    @PostMapping("/head")
    @ResponseBody
    public String uploadHead(Long id, String dataURL) {
        ReturnBean returnBean = new ReturnBean();
        String filePath;
        String imgName;
        if (id != null) {
            // 修改
            ClientRelease clientRelease = releaseService.selectById(id);
            String headUrl = clientRelease.getHeadUrl();
            Map<String, Object> map = GsonUtil.toMap(headUrl);
            filePath = (String) map.get("url");
            imgName = map.get("name") + "." + map.get("type");
        } else {
            // 新增
            Date date = new Date();
            long timeMillis = System.currentTimeMillis();
            filePath = FileUtil.getContentPath(date, String.valueOf(timeMillis));
            String suffix = ".png";
            imgName = timeMillis + suffix;
        }
        String path = fileServer + filePath + imgName;
        String ossPath = filePath + imgName;
        fileService.uploadFile(path, imgName, dataURL, ossPath);
        if (id != null){
            String imgUrl = AliyunOssUtil.getImgUrl(ossPath, 120, 120);
            returnBean.setData(imgUrl);
        }else{
            returnBean.setData(path);
        }
        return returnBean.toJsonString();
    }

    /**
     * 获取当前专辑头像
     *
     * @param response response
     * @throws IOException
     */
    @GetMapping("/head")
    @ResponseBody
    public void getHead(HttpServletResponse response) throws IOException {
        FileInputStream fis = null;
        OutputStream os = null;
        fis = new FileInputStream(releaseService.getCurReleaseHeadUrl());
        os = response.getOutputStream();
        int count = 0;
        byte[] buffer = new byte[1024 * 8];
        while ((count = fis.read(buffer)) != -1) {
            os.write(buffer, 0, count);
            os.flush();
        }
        fis.close();
        os.close();
    }

    @PutMapping("/info")
    @ResponseBody
    public String update(Long releaseId, String name, String intro, String field) {
        ReturnBean returnBean = new ReturnBean();
        boolean update = releaseService.update(releaseId, name, field, intro);
        if (!update) {
            returnBean.setReturnCode(ReturnCode.FAIL, null);
        }
        return returnBean.toJsonString();
    }

    @GetMapping("add")
    public String addPage(Model model) {
        List<SetField> fields = fieldService.getFields();
        model.addAttribute("fields", fields);
        return "resContent/add-release";
    }

    /**
     * 创建专辑
     *
     * @param name
     * @param head
     * @param intro
     * @param field
     * @return
     */
    @PostMapping("/info")
    @ResponseBody
    public String add(String name, String head, String intro, String field) {
        if (head.contains(fileServer)) {
            head = head.replaceAll(fileServer, "");
            head = "/" + head;
        }
        ReturnBean returnBean = new ReturnBean();
        ClientListen curListen = listenService.getCurListen();
        String listenId = curListen.getId();
        boolean addResult = releaseService.add(listenId, name, head, field, intro);
        if (!addResult) {
            returnBean.setReturnCode(ReturnCode.FAIL, null);
        }
        return returnBean.toJsonString();
    }

    /**
     * 删除专辑
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @ResponseBody
    public String delete(@PathVariable(value = "id") long id) {
        ReturnBean returnBean = new ReturnBean();
        ClientRelease clientRelease = releaseService.selectById(id);
        clientRelease.setStat(Constants.STAT_BLOCK);
        boolean update = releaseService.updateById(clientRelease);
        if (!update) {
            returnBean.setReturnCode(ReturnCode.FAIL, null);
        }
        //查询专辑的音频列表
        List<ResContent> list = contentService.getByRelease(id);
        for (ResContent resContent : list) {
            Long contentId = resContent.getId();
            //删除音频状态
            boolean delete = contentService.delete(contentId);
        }
        return returnBean.toJsonString();
    }

    @GetMapping("/modify/{id}")
    public String update(@PathVariable(value = "id") long id, Model model) {
        ClientRelease clientRelease = releaseService.selectById(id);
        ReleaseDto releaseDto = releaseService.wrapperDto(clientRelease);
        model.addAttribute("clientRelease", releaseDto);
        List<SetField> fields = fieldService.getFields();
        model.addAttribute("fields", fields);
        return "resContent/modify-release";
    }

}

