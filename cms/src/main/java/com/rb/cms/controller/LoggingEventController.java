package com.rb.cms.controller;


import com.common.base.constant.ReturnBean;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.common.base.util.SpageUtil;
import com.google.common.collect.Maps;
import com.rb.cms.entity.LoggingEvent;
import com.rb.cms.entity.LoggingEventException;
import com.rb.cms.service.LoggingEventExceptionService;
import com.rb.cms.service.LoggingEventService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-11-16
 */
@Controller
@RequestMapping("/loggingEvent")
public class LoggingEventController {

    @Autowired private LoggingEventService loggingEventService;

    @Autowired private LoggingEventExceptionService exceptionService;

    @GetMapping("index")
    public String index(){
        return "logging/list";
    }

    @GetMapping("list")
    @ResponseBody
    public Object list(@RequestParam(required = false, defaultValue = "1", value = "page") int page,
                       @RequestParam(required = false, defaultValue = "10", value = "rows") int rows,
                       @RequestParam(required = false, value = "sort") String sort,
                       @RequestParam(required = false, value = "order") String order,
                       @RequestParam(required = false, value = "search") String search){
        SpageUtil<LoggingEvent> spageUtil = new SpageUtil<>();
        if (StringUtils.isNotEmpty(sort) && StringUtils.isNotEmpty(order)) {
            sort = sort + " " + order;
            spageUtil.setSort(sort);
        }
        spageUtil.setPage(page);
        spageUtil.setStep(rows);
        spageUtil.setSearch(search);
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(1);
        spageUtil = loggingEventService.listByPage(params, spageUtil);
        List<LoggingEvent> loggingEventList = spageUtil.getRows();
        long total = spageUtil.getTotal();
        Map<String, Object> result = Maps.newHashMapWithExpectedSize(2);
        result.put("rows", loggingEventList);
        result.put("total", total);
        return result;
    }

    @GetMapping("detail/{eventId}")
    public String detail(@PathVariable(value = "eventId")Long eventId, Model model){
        LoggingEvent loggingEvent = loggingEventService.selectById(eventId);
        model.addAttribute("event", loggingEvent);
        List<LoggingEventException> exceptions = exceptionService.getExceptions(eventId);
        model.addAttribute("exceptions", exceptions);
        return "logging/detail";
    }

    @PutMapping("stat/{ids}")
    @ResponseBody
    public ReturnBean updateStat(@PathVariable(value = "ids") String ids, String stat){
        LoggingEvent event = new LoggingEvent();
        String[] split = ids.split(",");
        for (String id:split) {
            event.setEventId(Long.valueOf(id));
            event.setArg0(stat);
            boolean update = loggingEventService.updateById(event);
            if (!update){
                throw new BusinessException(ReturnCode.FAIL);
            }
        }
        return new ReturnBean();
    }

}

