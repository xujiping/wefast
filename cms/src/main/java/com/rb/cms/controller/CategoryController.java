package com.rb.cms.controller;

import com.common.base.constant.Constants;
import com.common.base.constant.ReturnBean;
import com.common.base.util.SpageUtil;
import com.google.common.collect.Maps;
import com.rb.cms.entity.Category;
import com.rb.cms.entity.dto.CategoryDto2;
import com.rb.cms.service.CategoryService;
import com.rb.cms.util.UrlUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 分类表 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-11-20
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Value("${file.server}")
    private String fileServer;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("index")
    public String index() {
        return "category/list";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable(value = "id") String id, Model model) {
        String subject = "";
        Category category = categoryService.selectById(id);
        if (category != null) {
            subject = category.getSubject();
            model.addAttribute("category", category);
        }
        List<Category> topCategories = categoryService.getLevel(1, subject);
        model.addAttribute("topCategories", topCategories);
        return "category/edit";
    }

    /**
     * 查询列表.
     *
     * @param page  第几页
     * @param rows  每页多少行
     * @param sort  排序（未实现）
     * @param order 排序列
     * @return Map
     */
    @RequestMapping(value = "list")
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "1", value = "page") int page,
            @RequestParam(required = false, defaultValue = "10", value = "rows") int rows,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order,
            @RequestParam(required = false, value = "search") String search,
            @RequestParam(required = false, value = "level") Integer level,
            @RequestParam(required = false, value = "parent") String parent,
            @RequestParam(required = false, value = "stat") String stat,
            @RequestParam(required = false, value = "subject") String subject,
            @RequestParam(required = false, value = "value") String value) {
        SpageUtil<Category> spageUtil = new SpageUtil<>();
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(4);
        if (StringUtils.isNotEmpty(sort) && StringUtils.isNotEmpty(order)) {
            sort = sort + " " + order;
            spageUtil.setSort(sort);
        }
        spageUtil.setPage(page);
        spageUtil.setStep(rows);
        spageUtil.setSearch(search);
        if (StringUtils.isNotEmpty(parent)) {
            params.put("parent", parent);
            level = 2;
        }
        if (StringUtils.isNotEmpty(value)) {
            params.put("value", value);
        }
        if (StringUtils.isNotEmpty(stat)) {
            params.put("stat", stat);
        }
        if (level == null) {
            level = 0;
        }
        if (StringUtils.isNotEmpty(subject)) {
            params.put("subject", subject);
        }
        params.put("level", level);
        spageUtil = categoryService.listByMap(params, spageUtil);
        long total = spageUtil.getTotal();
        Map<String, Object> result = Maps.newHashMapWithExpectedSize(2);
        List<Category> fields = spageUtil.getRows();
        List<CategoryDto2> categoryDto2List = new ArrayList<>();
        if (fields != null && fields.size() > 0) {
            categoryDto2List = fields.stream().map(category -> categoryService.wrapper(category)).collect(Collectors
                    .toList());
        }
        result.put("rows", fields);
        result.put("total", total);
        return result;
    }

    /**
     * 保存
     *
     * @param name
     * @param level
     * @param parent
     * @param seq
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public ReturnBean update(Integer id, String name, int level, String parent, int seq) {
        ReturnBean rb = new ReturnBean();
        Category category = new Category();
        category.setLevel(level);
        category.setName(name);
        category.setSeq(seq);
        category.setStat(Constants.STAT_NORMAL);
        if (level != Constants.LEVEL_1 && StringUtils.isNotEmpty(parent)) {
            category.setParentId(Integer.valueOf(parent));
        }
            //更新
            category.setId(id);
            categoryService.updateById(category);
        return rb;
    }

}

