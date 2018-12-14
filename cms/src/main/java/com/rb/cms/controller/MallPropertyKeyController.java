package com.rb.cms.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.base.constant.ReturnBean;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.rb.cms.entity.MallCategory;
import com.rb.cms.entity.MallPropertyKey;
import com.rb.cms.entity.MallPropertyValue;
import com.rb.cms.entity.dto.CategoryDto;
import com.rb.cms.entity.dto.PropertyDto;
import com.rb.cms.service.MallCategoryService;
import com.rb.cms.service.MallPropertyKeyService;
import com.rb.cms.service.MallPropertyValueService;
import com.rb.cms.util.JsonListUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 属性名表 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-06-19
 */
@Controller
@RequestMapping("/mallPropertyKey")
public class MallPropertyKeyController {

    @Autowired
    private MallPropertyKeyService propertyKeyService;
    @Autowired
    private MallPropertyValueService propertyValueService;
    @Autowired
    private MallCategoryService categoryService;

    @GetMapping("list")
    public String index() {
        return "property/property-list";
    }

    /**
     * 列表
     *
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @param name
     * @return
     */
    @GetMapping("properties")
    @ResponseBody
    public Object users(@RequestParam(required = false, defaultValue = "1", value = "page") int page,
                        @RequestParam(required = false, defaultValue = "10", value = "rows") int rows,
                        @RequestParam(required = false, value = "sort") String sort,
                        @RequestParam(required = false, value = "order") String order,
                        @RequestParam(required = false, value = "name") String name) {
        PropertyDto property = new PropertyDto();
        //条件
        Wrapper<MallPropertyKey> wrapper = new EntityWrapper<>();
        if (StringUtils.isNotEmpty(name)) {
            wrapper.like("name", name);
        }
        //分页
        Page<MallPropertyKey> objectPage = new Page<>(page, rows);
        List<MallPropertyKey> propertyKeys = propertyKeyService.selectPage(objectPage, wrapper).getRecords();
        List<PropertyDto> propertyList = propertyKeys.stream().map(this::wrapperProperty)
                .collect(Collectors.toList());
        int count = propertyKeyService.selectCount(wrapper);
        return JsonListUtil.toJson(ReturnCode.SUCCESS.code(), ReturnCode.SUCCESS.msg(), (long) count, propertyList);
    }

    /**
     * 包装
     *
     * @param mallPropertyKey
     * @return
     */
    private PropertyDto wrapperProperty(MallPropertyKey mallPropertyKey) {
        PropertyDto propertyDto = new PropertyDto();
        if (mallPropertyKey == null) {
            return propertyDto;
        }
        BeanUtils.copyProperties(mallPropertyKey, propertyDto);
        //属性值
        Wrapper<MallPropertyValue> wrapper = new EntityWrapper<>();
        wrapper.eq("property_key_id", mallPropertyKey.getId());
        List<MallPropertyValue> propertyValues = propertyValueService.selectList(wrapper);
        String[] values = new String[propertyValues.size()];
        for (int i = 0; i < propertyValues.size(); i++) {
            values[i] = propertyValues.get(i).getValue();
        }
        propertyDto.setValues(values);
        //父属性名称
        Integer pid = mallPropertyKey.getPid();
        String pName = "无";
        if (pid != 0) {
            MallPropertyKey parentPropertyKey = propertyKeyService.selectById(mallPropertyKey.getPid());
            pName = parentPropertyKey.getName();
        }
        propertyDto.setpName(pName);
        //分类名称
        MallCategory category = categoryService.selectById(mallPropertyKey.getCategoryId());
        propertyDto.setCategoryName(category.getName());
        return propertyDto;
    }

    /**
     * 新增页面
     *
     * @return
     */
    @GetMapping("add")
    public String addPage(Model model) {
        Wrapper<MallPropertyKey> wrapper = new EntityWrapper<>();
        wrapper.eq("pid", 0);
        List<MallPropertyKey> propertyKeys = propertyKeyService.selectList(wrapper);
        model.addAttribute("topKeys", propertyKeys);
        //一级分类
        Wrapper<MallCategory> wrapper1 = new EntityWrapper<>();
        wrapper1.eq("level", 1);
        List<MallCategory> categoryList = categoryService.selectList(wrapper1);
        model.addAttribute("firstLevel", categoryList);
        return "property/property-add";
    }

    /**
     * 新增
     *
     * @param name
     * @param pid
     * @return
     */
    @PostMapping("")
    @ResponseBody
    public ReturnBean add(@RequestParam(value = "values[]") String[] values, String name, Integer pid, Integer
            categoryId) {
        //判断是否已存在
        Wrapper<MallPropertyKey> wrapper = new EntityWrapper<>();
        wrapper.eq("name", name);
        MallPropertyKey propertyKey = propertyKeyService.selectOne(wrapper);
        Date date = new Date();
        if (propertyKey == null) {
            //新增key
            propertyKey = new MallPropertyKey();
            propertyKey.setName(name);
            propertyKey.setPid(pid);
            propertyKey.setCreateTime(date);
            propertyKey.setUpdateTime(date);
            propertyKey.setCategoryId(categoryId);
            boolean insert = propertyKey.insert();
            if (!insert) {
                throw new BusinessException(ReturnCode.FAIL);
            }
        }
        //新增value
        MallPropertyValue propertyValue = null;
        for (String value : values) {
            propertyValue = new MallPropertyValue();
            propertyValue.setValue(value);
            propertyValue.setPropertyKeyId(propertyKey.getId());
            propertyValue.setCreateTime(date);
            propertyValue.setUpdateTime(date);
            boolean insert = propertyValue.insert();
            if (!insert) {
                throw new BusinessException(ReturnCode.FAIL);
            }
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
        MallPropertyKey propertyKey = propertyKeyService.selectById(id);
        PropertyDto propertyDto = wrapperProperty(propertyKey);
        model.addAttribute("property", propertyDto);
        //一级属性
        Wrapper<MallPropertyKey> wrapper = new EntityWrapper<>();
        wrapper.eq("pid", 0);
        List<MallPropertyKey> propertyKeys = propertyKeyService.selectList(wrapper);
        model.addAttribute("topKeys", propertyKeys);
        //一级分类
        Wrapper<MallCategory> wrapper1 = new EntityWrapper<>();
        wrapper1.eq("level", 1);
        List<MallCategory> categoryList = categoryService.selectList(wrapper1);
        model.addAttribute("firstLevel", categoryList);
        //分类属性
        CategoryDto categoryDto = categoryService.getDtoById(String.valueOf(propertyDto.getCategoryId()));
        model.addAttribute("category", categoryDto);
        return "property/property-edit";
    }

    /**
     * 修改属性名
     *
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    @ResponseBody
    public ReturnBean edit(@PathVariable(value = "id") String id,
                           @RequestParam(value = "values[]") String[] values,
                           String name, Integer pid, Integer categoryId) {
        Date date = new Date();
        MallPropertyKey propertyKey = new MallPropertyKey();
        propertyKey.setId(Integer.parseInt(id));
        propertyKey.setName(name);
        propertyKey.setPid(pid);
        propertyKey.setCategoryId(categoryId);
        propertyKey.setUpdateTime(date);
        boolean update = propertyKey.updateById();
        if (!update) {
            throw new BusinessException(ReturnCode.FAIL);
        }
        //修改属性值
        propertyValueService.updateValue(id, values);
        return new ReturnBean();
    }


    /**
     * 删除属性名
     *
     * @param ids id数组
     * @return
     */
    @DeleteMapping("/{ids}")
    @ResponseBody
    public ReturnBean deleteAll(@PathVariable(value = "ids") String[] ids) {
        //判断是否有相关子分类
        Wrapper<MallPropertyKey> wrapper = new EntityWrapper<>();
        wrapper.in("pid", ids);
        int count = propertyKeyService.selectCount(wrapper);
        if (count > 0) {
            return new ReturnBean(ReturnCode.FAIL, null);
        }
        for (String id : ids) {
            //删除对应属性值
            propertyValueService.deleteByKeyId(id);
            //删除属性
            boolean delete = propertyKeyService.deleteById(Integer.parseInt(id));
            if (!delete) {
                return new ReturnBean(ReturnCode.FAIL, id);
            }
        }
        return new ReturnBean();
    }

    /**
     * 删除属性值
     *
     * @param id
     * @return
     */
    @DeleteMapping("value/{id}")
    public ReturnBean deleteValue(@PathVariable(value = "id") Integer id) {
        boolean delete = propertyValueService.deleteById(id);
        if (!delete) {
            throw new BusinessException(ReturnCode.FAIL);
        }
        return new ReturnBean();
    }

    /**
     * 根据Pid查询
     *
     * @param pid
     * @return
     */
    @GetMapping("pid/{pid}")
    @ResponseBody
    public ReturnBean getByPid(@PathVariable(value = "pid") Integer pid) {
        Wrapper<MallPropertyKey> wrapper = new EntityWrapper<>();
        wrapper.eq("pid", pid);
        List<MallPropertyKey> propertyList = propertyKeyService.selectList(wrapper);
        return new ReturnBean(ReturnCode.SUCCESS, propertyList);
    }

}

