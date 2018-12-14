package com.rb.cms.controller;


import com.common.base.constant.ReturnCode;
import com.common.base.util.SpageUtil;
import com.rb.cms.entity.CmsRole;
import com.rb.cms.service.CmsRoleService;
import com.rb.cms.util.JsonListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-11-09
 */
@Controller
@RequestMapping("/cmsRole")
public class CmsRoleController {

    @Autowired
    private CmsRoleService roleService;

    /**
     * 默认页
     *
     * @return
     */
    @GetMapping("index")
    public String index() {
        return "role/list";
    }

    /**
     * 新增页
     *
     * @return
     */
    @GetMapping("add")
    public String add() {
        return "role/add";
    }

    /**
     * 分页列表
     *
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
        SpageUtil<CmsRole> spageUtil = new SpageUtil<>();
        spageUtil = roleService.listByPage(null, spageUtil);
        long total = spageUtil.getTotal();
        List<CmsRole> list = spageUtil.getRows();
        return JsonListUtil.toJson(ReturnCode.SUCCESS.code(), ReturnCode.SUCCESS.msg(), total, list);
    }

}

