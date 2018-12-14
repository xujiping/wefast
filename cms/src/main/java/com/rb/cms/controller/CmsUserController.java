package com.rb.cms.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.base.constant.Constants;
import com.common.base.constant.ReturnBean;
import com.common.base.constant.ReturnCode;
import com.rb.cms.entity.CmsUser;
import com.rb.cms.service.CmsUserService;
import com.rb.cms.util.DateUtils;
import com.rb.cms.util.JsonListUtil;
import com.rb.cms.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-06-19
 */
@Controller
@RequestMapping("/admin")
public class CmsUserController {

    @Autowired
    private CmsUserService cmsUserService;

    @GetMapping("list")
    @RequiresRoles(value = "admin")
    public String index() {
        return "admin/admin-list";
    }


    @GetMapping("users")
    @ResponseBody
    public Object users(@RequestParam(required = false, defaultValue = "1", value = "page") int page,
                        @RequestParam(required = false, defaultValue = "10", value = "rows") int rows,
                        @RequestParam(required = false, value = "sort") String sort,
                        @RequestParam(required = false, value = "order") String order,
                        @RequestParam(required = false, value = "sTime") String sTime,
                        @RequestParam(required = false, value = "eTime") String eTime,
                        @RequestParam(required = false, value = "username") String username) {
        CmsUser cmsUser = new CmsUser();
        //条件
        Wrapper<CmsUser> wrapper = new EntityWrapper<>();
        if (StringUtils.isNotEmpty(sTime)) {
            wrapper.ge("create_time", DateUtils.convertToDate(sTime));
        }
        if (StringUtils.isNotEmpty(username)) {
            wrapper.eq("username", username);
        }
        //分页
        Page<CmsUser> objectPage = new Page<>(page, rows);
        List<CmsUser> users = cmsUser.selectPage(objectPage, wrapper).getRecords();
        int count = cmsUser.selectCount(wrapper);
        return JsonListUtil.toJson(ReturnCode.SUCCESS.code(), ReturnCode.SUCCESS.msg(), (long) count, users);
    }

    @GetMapping("add")
    public String addPage() {
        return "admin/admin-add";
    }

    /**
     * 新增用户
     *
     * @param username 用户名
     * @param phone    手机
     * @param email    邮箱
     * @param password 密码
     * @return ReturnBean
     */
    @PostMapping("")
    @ResponseBody
    public ReturnBean add(@PathParam(value = "username") String username,
                          @PathParam(value = "phone") String phone,
                          @PathParam(value = "email") String email,
                          @PathParam(value = "password") String password) {
        CmsUser cmsUser = new CmsUser();
        cmsUser.setUsername(username);
        String newPassword = MD5Util.MD5(password + Constants.SALT);
        cmsUser.setPassword(newPassword);
        cmsUser.setPhone(phone);
        cmsUser.setEmail(email);
        Date date = new Date();
        cmsUser.setUpdateTime(date);
        cmsUser.setCreateTime(date);
        boolean insert = cmsUser.insert();
        if (!insert) {
            return new ReturnBean(ReturnCode.FAIL, null);
        }
        return new ReturnBean();
    }

    /**
     * 删除
     *
     * @param ids id数组
     * @return
     */
    @DeleteMapping("/{ids}")
    @ResponseBody
    public ReturnBean deleteAll(@PathVariable(value = "ids") String[] ids) {
        for (String id : ids) {
            boolean delete = cmsUserService.deleteById(Integer.parseInt(id));
            if (!delete) {
                return new ReturnBean(ReturnCode.FAIL, id);
            }
        }
        return new ReturnBean();
    }

    @GetMapping("edit/{id}")
    public String editPage(@PathVariable(value = "id") String id, Model model) {
        CmsUser cmsUser = cmsUserService.selectById(id);
        model.addAttribute("user", cmsUser);
        return "admin/admin-edit";
    }

    /**
     * 修改
     *
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    @ResponseBody
    public ReturnBean edit(@PathVariable(value = "id") String id, String username, String phone, String email, String
            password) {
        ReturnBean rb = new ReturnBean();
        CmsUser cmsUser = new CmsUser();
        cmsUser.setId(Integer.parseInt(id));
        cmsUser.setUsername(username);
        if (StringUtils.isNotEmpty(password)) {
            String newPassword = MD5Util.MD5(password + Constants.SALT);
            cmsUser.setPassword(newPassword);
        }
        cmsUser.setPhone(phone);
        cmsUser.setEmail(email);
        cmsUser.setUpdateTime(new Date());
        boolean update = cmsUser.updateById();
        if (!update) {
            return new ReturnBean(ReturnCode.FAIL, null);
        }
        return rb;
    }

}

