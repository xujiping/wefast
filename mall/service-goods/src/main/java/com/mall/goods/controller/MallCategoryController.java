package com.mall.goods.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.base.exception.BusinessException;
import com.common.base.constant.ReturnBean;
import com.common.base.constant.ReturnCode;
import com.mall.goods.entity.MallCategory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品分类表 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-06-14
 */
@RestController
@RequestMapping("/mallCategory")
@Api(tags = "商品类别接口")
@Transactional(rollbackFor = BusinessException.class)
public class MallCategoryController {

    @ApiOperation(value = "查询所有类别", notes = "查询所有的类别")
    @GetMapping("categories")
    public String all() throws Exception {
        ReturnBean returnBean = new ReturnBean();
        MallCategory category = new MallCategory();
        List<MallCategory> categories = category.selectAll();
        returnBean.setReturnCode(ReturnCode.SUCCESS, categories);
        returnBean.setCount((long) categories.size());
        return returnBean.toJsonString();
    }

    @ApiOperation(value = "分页查询", notes = "分页查询类别列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", required = false, dataType = "int", paramType =
                    "query", defaultValue = "1"),
            @ApiImplicitParam(name = "rows", value = "每页行数", required = false, dataType = "int", paramType =
                    "query", defaultValue = "10")
    })
    @GetMapping("list")
    public String list(@RequestParam(required = false) int page,
                       @RequestParam(required = false) int rows) {
        ReturnBean returnBean = new ReturnBean();
        MallCategory category = new MallCategory();
        //条件
        EntityWrapper<MallCategory> wrapper = new EntityWrapper<>();
        //分页
        Page<MallCategory> objectPage = new Page<>(page, rows);
        List<MallCategory> categories = category.selectPage(objectPage, wrapper).getRecords();
        int count = category.selectCount(wrapper);
        returnBean.setReturnCode(ReturnCode.SUCCESS, categories);
        returnBean.setCount((long) count);
        return returnBean.toJsonString();
    }

    @ApiOperation(value = "新增分类", notes = "新增分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "分类名称", required = true, dataType = "String", paramType =
                    "query"),
            @ApiImplicitParam(name = "pid", value = "父分类ID", required = false, dataType = "int", paramType =
                    "query", defaultValue = "0")
    })
    @PostMapping("category")
    public String add(@RequestParam String name,
                      @RequestParam(required = false) Integer pid) {
        MallCategory category = new MallCategory();
        category.setName(name);
        category.setPid(pid);
        boolean insert = category.insert();
        if (!insert) {
            throw new BusinessException(ReturnCode.FAIL);
        }
        return new ReturnBean().toJsonString();
    }

    @ApiOperation(value = "修改分类", notes = "根据主键ID修改分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "分类ID", required = true, dataType = "int", paramType =
                    "path"),
            @ApiImplicitParam(name = "name", value = "分类名称", required = false, dataType = "String", paramType =
                    "query"),
            @ApiImplicitParam(name = "pid", value = "父分类ID", required = false, dataType = "int", paramType =
                    "query", defaultValue = "0")
    })
    @PutMapping("category/{id}")
    public String update(@PathVariable(value = "id") Integer id,
                         @RequestParam(required = false) String name,
                         @RequestParam(required = false) Integer pid) {
        MallCategory category = new MallCategory();
        category.setId(id);
        MallCategory select = category.selectById();
        if (select == null) {
            return new BusinessException(ReturnCode.NULL_INFO).toString();
        }
        category.setName(name);
        category.setPid(pid);
        boolean update = category.updateById();
        if (!update) {
            throw new BusinessException(ReturnCode.FAIL);
        }
        return new ReturnBean().toJsonString();
    }

    @ApiOperation(value = "删除分类", notes = "根据主键ID删除分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "分类ID", required = true, dataType = "int", paramType =
                    "path")
    })
    @DeleteMapping("category/{id}")
    public String update(@PathVariable(value = "id") Integer id) {
        MallCategory category = new MallCategory();
        category.setId(id);
        MallCategory select = category.selectById();
        if (select == null) {
            return new BusinessException(ReturnCode.NULL_INFO).toString();
        }
        boolean delete = category.deleteById();
        if (!delete) {
            throw new BusinessException(ReturnCode.FAIL);
        }
        return new ReturnBean().toJsonString();
    }

}

