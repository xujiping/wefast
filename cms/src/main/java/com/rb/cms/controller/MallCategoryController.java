package com.rb.cms.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.base.constant.ReturnBean;
import com.common.base.constant.ReturnCode;
import com.rb.cms.entity.MallCategory;
import com.rb.cms.entity.dto.CategoryDto;
import com.rb.cms.service.MallCategoryService;
import com.rb.cms.util.JsonListUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 商品分类表 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-06-19
 */
@Controller
@RequestMapping("/mallCategory")
public class MallCategoryController {

    @Autowired
    private MallCategoryService categoryService;

    @GetMapping("list")
    public String index() {
        return "mallCategory/mallCategory-list";
    }

    /**
     * 列表
     *
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @param name
     * @param seller
     * @param description
     * @return
     */
    @GetMapping("categories")
    @ResponseBody
    public Object users(@RequestParam(required = false, defaultValue = "1", value = "page") int page,
                        @RequestParam(required = false, defaultValue = "10", value = "rows") int rows,
                        @RequestParam(required = false, value = "sort") String sort,
                        @RequestParam(required = false, value = "order") String order,
                        @RequestParam(required = false, value = "name") String name,
                        @RequestParam(required = false, value = "level") String level,
                        @RequestParam(required = false, value = "seller") String seller,
                        @RequestParam(required = false, value = "description") String description) {
        MallCategory mallCategory = new MallCategory();
        //条件
        Wrapper<MallCategory> wrapper = new EntityWrapper<>();
        if (StringUtils.isNotEmpty(name)) {
            wrapper.like("name", name);
        }
        if (StringUtils.isNotEmpty(level)) {
            wrapper.eq("level", level);
        }
        //分页
        Page<MallCategory> objectPage = new Page<>(page, rows);
        List<MallCategory> categoryList = mallCategory.selectPage(objectPage, wrapper).getRecords();
        int count = mallCategory.selectCount(wrapper);
        return JsonListUtil.toJson(ReturnCode.SUCCESS.code(), ReturnCode.SUCCESS.msg(), (long) count, categoryList);
    }

    /**
     * 新增页面
     *
     * @return
     */
    @GetMapping("add")
    public String addPage() {
        return "mallCategory/mallCategory-add";
    }

    /**
     * 新增
     * @param name
     * @param pid
     * @param level
     * @return
     */
    @PostMapping("")
    @ResponseBody
    public ReturnBean add(String name, Integer pid, Integer level) {
        MallCategory category = new MallCategory();
        if (level == 1){
            pid = 0;
        }
        category.setPid(pid);
        category.setLevel(level);
        category.setName(name);
        Date date = new Date();
        category.setCreateTime(date);
        category.setUpdateTime(date);
        boolean insert = category.insert();
        if (!insert) {
            return new ReturnBean(ReturnCode.FAIL, null);
        }
        return new ReturnBean();
    }


    /**
     * 修改页面
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("edit/{id}")
    public String editPage(@PathVariable(value = "id") String id, Model model) {
        CategoryDto categoryDto = categoryService.getDtoById(id);
        model.addAttribute("category", categoryDto);
        return "mallCategory/mallCategory-edit";
    }

    /**
     * 修改
     *
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    @ResponseBody
    public ReturnBean edit(@PathVariable(value = "id") String id, String name, String pid, Integer level) {
        Date date = new Date();
        MallCategory category = new MallCategory();
        category.setId(Integer.parseInt(id));
        category.setName(name);
        category.setPid(Integer.parseInt(pid));
        category.setUpdateTime(date);
        category.setLevel(level);
        boolean update = category.updateById();
        if (!update) {
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
        Wrapper<MallCategory> wrapper = null;
        //判断是否有相关子分类
        wrapper = new EntityWrapper<>();
        wrapper.in("pid", ids);
        int count = categoryService.selectCount(wrapper);
        if (count > 0){
            return new ReturnBean(ReturnCode.FAIL, null);
        }
        for (String id : ids) {
            boolean delete = categoryService.deleteById(Integer.parseInt(id));
            if (!delete) {
                return new ReturnBean(ReturnCode.FAIL, id);
            }
        }
        return new ReturnBean();
    }

    /**
     * 根据级别查询列表
     * @param level
     * @return
     */
    @GetMapping("level/{level}")
    @ResponseBody
    public ReturnBean getByLevel(@PathVariable(value = "level") Integer level){
        MallCategory category = new MallCategory();
        List<MallCategory> categoryList = category.selectList("level", level);
        return new ReturnBean(ReturnCode.SUCCESS, categoryList);
    }

    /**
     * 根据Pid查询
     * @param pid
     * @return
     */
    @GetMapping("pid/{pid}")
    @ResponseBody
    public ReturnBean getByPid(@PathVariable(value = "pid") Integer pid){
        Wrapper<MallCategory> wrapper = new EntityWrapper<>();
        wrapper.eq("pid", pid);
        List<MallCategory> categoryList = categoryService.selectList(wrapper);
        return new ReturnBean(ReturnCode.SUCCESS, categoryList);
    }

}

