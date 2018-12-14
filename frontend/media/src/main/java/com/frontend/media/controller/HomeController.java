package com.frontend.media.controller;

import com.common.base.util.StringUtil;
import com.frontend.media.entity.ClientListen;
import com.frontend.media.entity.ResContent;
import com.frontend.media.service.ClientListenService;
import com.frontend.media.service.ClientReleaseService;
import com.frontend.media.service.ContentStatisticalService;
import com.frontend.media.service.ResContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author xujiping
 * @date 2018/7/16 11:27
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired private ClientListenService clientListenService;

    @Autowired private ContentStatisticalService contentStatisticalService;

    @Autowired private ClientReleaseService releaseService;

    @Autowired private ResContentService contentService;

    @GetMapping("")
    public String index(Model model){
        ClientListen clientListen = clientListenService.getCurListen();
        String listenId = clientListen.getId();
        String name = clientListen.getName();
        clientListen.setName(StringUtil.resolveToEmojiFromByte(name));
        model.addAttribute("curListen", clientListen);
        // todo 查询时间太长
//        //昨日播放量
//        Long yestodayPlayCount = contentStatisticalService.yestodayPlayCount(listenId);
//        model.addAttribute("yestodayPlayCount", yestodayPlayCount);
//        //昨日订阅量
//        Long yestodayFollowCount = releaseService.yestodayFollowCount();
//        model.addAttribute("yestodayFollowCount", yestodayFollowCount);
        //发布的音频数量
        List<ResContent> curUserContents = contentService.getCurUserContents();
        model.addAttribute("contentsCount", curUserContents.size());
        return "home";
    }
}
