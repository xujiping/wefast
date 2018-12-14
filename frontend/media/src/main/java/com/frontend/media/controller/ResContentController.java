package com.frontend.media.controller;


import com.common.base.constant.Constants;
import com.common.base.constant.ReturnBean;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.common.base.util.SpageUtil;
import com.frontend.media.controller.dto.ContentDto;
import com.frontend.media.entity.ResContent;
import com.frontend.media.service.ResContentService;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 发布者发布的资源 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-07-24
 */
@Controller
@RequestMapping("/resContent")
public class ResContentController {

    @Autowired
    private ResContentService contentService;

    @GetMapping("content/{releaseId}")
    public String content(@PathVariable(value = "releaseId") long releaseId, Model model) {
        Long total = contentService.coutByRelease(releaseId);
        model.addAttribute("releaseId", releaseId);
        model.addAttribute("total", total);
        return "resContent/content";
    }

    /**
     * 查询音频列表.
     *
     * @param page  第几页
     * @param rows  每页多少行
     * @param sort  排序（未实现）
     * @param order 排序列
     * @return Map
     */
    @RequestMapping(value = "list")
    @ResponseBody
    public String list(
            @RequestParam(required = false, defaultValue = "1", value = "page") int page,
            @RequestParam(required = false, defaultValue = "10", value = "rows") int rows,
            @RequestParam Long releaseId,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order,
            @RequestParam(required = false, value = "search") String search,
            @RequestParam(required = false, value = "stat") String stat) {
        ReturnBean returnBean = new ReturnBean();
        SpageUtil<ResContent> spageUtil = new SpageUtil<>(page, rows);
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
        params.put("releaseId", releaseId);
        if (StringUtils.isNotEmpty(stat) && !stat.equals(Constants.STAT_ALL)) {
            params.put("stat", stat);
        }
        spageUtil = contentService.listByPage(params, spageUtil);
        long total = spageUtil.getTotal();
        List<ResContent> contentList = spageUtil.getRows();
        List<ContentDto> contentDtoList = new ArrayList<>();
        if (contentList != null && contentList.size() > 0) {
            contentDtoList = contentList.stream().map(resContent -> contentService.wrapperDto(resContent)).collect
                    (Collectors.toList());
        }
        returnBean.setCount(total);
        returnBean.setData(contentDtoList);
        return returnBean.toJsonString();
    }

    /**
     * 查询音频信息
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @ResponseBody
    public String getInfo(@PathVariable(value = "id") String id) {
        ReturnBean returnBean = new ReturnBean();
        ResContent resContent = contentService.selectById(id);
        returnBean.setData(resContent);
        return returnBean.toJsonString();
    }

    /**
     * 更新音频信息
     *
     * @param contentId
     * @param title
     * @param summary
     * @return
     */
    @PutMapping("info")
    @ResponseBody
    public String update(Long contentId, String title, String summary) {
        ReturnBean returnBean = new ReturnBean();
        ResContent resContent = new ResContent();
        resContent.setId(contentId);
        resContent.setTitle(title);
        resContent.setSummary(summary);
        resContent.setUpdatedDate(new Date());
        resContent.setStat("audit");
        boolean update = contentService.updateById(resContent);
        if (!update) {
            throw new BusinessException(ReturnCode.FAIL);
        }
        return returnBean.toJsonString();
    }

    /**
     * 删除音频
     *
     * @param id
     * @return
     */
    @DeleteMapping("info/{id}")
    @ResponseBody
    public String delete(@PathVariable(value = "id") Long id) {
        ReturnBean returnBean = new ReturnBean();
        boolean delete = contentService.delete(id);
        if (!delete) {
            throw new BusinessException(ReturnCode.FAIL);
        }
        return returnBean.toJsonString();
    }

    /**
     * 置顶
     *
     * @param id
     * @return
     */
    @PutMapping("top/{id}")
    @ResponseBody
    public String setTop(@PathVariable(value = "id") Long id) {
        ReturnBean returnBean = new ReturnBean();
        boolean delete = contentService.setTop(id);
        if (!delete) {
            throw new BusinessException(ReturnCode.FAIL);
        }
        return returnBean.toJsonString();
    }

}

