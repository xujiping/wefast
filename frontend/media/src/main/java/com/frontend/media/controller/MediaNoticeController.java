package com.frontend.media.controller;

import com.common.base.constant.ReturnBean;
import com.common.base.util.SpageUtil;
import com.frontend.media.entity.MediaNotice;
import com.frontend.media.service.MediaNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 自媒体平台公告 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-09-03
 */
@Controller
@RequestMapping("/mediaNotice")
public class MediaNoticeController {

    @Autowired private MediaNoticeService noticeService;

    /**
     * 查询列表.
     *
     * @param page  第几页
     * @param rows  每页多少行
     * @param sort  排序（未实现）
     * @param order 排序列
     * @return Map
     */
    @GetMapping(value = "list")
    @ResponseBody
    public String list(
            @RequestParam(required = false, defaultValue = "1", value = "page") int page,
            @RequestParam(required = false, defaultValue = "10", value = "rows") int rows,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order,
            @RequestParam(required = false, value = "search") String search) {
        ReturnBean returnBean = new ReturnBean();
        SpageUtil<MediaNotice> spageUtil = new SpageUtil<>(page, rows);
        spageUtil.setSort("create_time desc");
        spageUtil = noticeService.listByPage(null, spageUtil);
        long total = spageUtil.getTotal();
        List<MediaNotice> list = spageUtil.getRows();
        returnBean.setCount(total);
        returnBean.setData(list);
        return returnBean.toJsonString();
    }

    @GetMapping("{id}")
    public String detail(@PathVariable(value = "id")int id, Model model){
        MediaNotice mediaNotice = noticeService.selectById(id);
        model.addAttribute("notice", mediaNotice);
        return "notice/index";
    }

}

