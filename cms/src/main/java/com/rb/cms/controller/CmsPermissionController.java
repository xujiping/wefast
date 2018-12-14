package com.rb.cms.controller;


import com.common.base.constant.ReturnCode;
import com.common.base.util.SpageUtil;
import com.rb.cms.entity.CmsPermission;
import com.rb.cms.entity.dto.PermissionDto;
import com.rb.cms.service.CmsPermissionService;
import com.rb.cms.util.JsonListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-11-09
 */
@Controller
@RequestMapping("/cmsPermission")
public class CmsPermissionController {

    @Autowired private CmsPermissionService permissionService;

    /**
     * 默认页
     * @return
     */
    @GetMapping("index")
    public String index() {
        return "permission/list";
    }

    /**
     * 分页列表
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @GetMapping("list")
    @ResponseBody
    public Object list(@RequestParam(required = false, defaultValue = "1", value = "page") int page,
                       @RequestParam(required = false, defaultValue = "10", value = "rows") int rows,
                       @RequestParam(required = false, value = "sort") String sort,
                       @RequestParam(required = false, value = "order") String order) {
        SpageUtil<CmsPermission> spageUtil = new SpageUtil<>();
        spageUtil = permissionService.listByPage(null, spageUtil);
        long total = spageUtil.getTotal();
        List<CmsPermission> list = spageUtil.getRows();
        List<PermissionDto> permissionDtos = new ArrayList<>();
        if (list != null && list.size() > 0){
            permissionDtos = list.stream().map(permission -> permissionService.wrapper(permission)).collect
                    (Collectors.toList());
        }
        return JsonListUtil.toJson(ReturnCode.SUCCESS.code(), ReturnCode.SUCCESS.msg(), total, permissionDtos);
    }

}

