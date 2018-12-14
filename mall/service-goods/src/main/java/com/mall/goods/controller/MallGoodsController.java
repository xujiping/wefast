package com.mall.goods.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.base.constant.ReturnBean;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.mall.goods.entity.MallCategory;
import com.mall.goods.entity.MallGoods;
import com.mall.goods.service.MallCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-06-14
 */
@RestController
@RequestMapping("/mallGoods")
@Api(tags = "商品接口")
public class MallGoodsController {

    @Autowired private MallCategoryService categoryService;

    @ApiOperation(value = "商品列表", notes = "分页查询商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", required = false, dataType = "int", paramType =
                    "query", defaultValue = "1"),
            @ApiImplicitParam(name = "rows", value = "每页行数", required = false, dataType = "int", paramType =
                    "query", defaultValue = "10")
    })
    @GetMapping("list")
    public String list(@RequestParam(required = false, defaultValue = "1") int page,
                       @RequestParam(required = false, defaultValue = "10") int rows) {
        ReturnBean returnBean = new ReturnBean();
        MallGoods goods = new MallGoods();
        //条件
        EntityWrapper<MallGoods> wrapper = new EntityWrapper<>();
        //分页
        Page<MallGoods> objectPage = new Page<>(page, rows);
        List<MallGoods> categories = goods.selectPage(objectPage, wrapper).getRecords();
        int count = goods.selectCount(wrapper);
        returnBean.setReturnCode(ReturnCode.SUCCESS, categories);
        returnBean.setCount((long) count);
        return returnBean.toJsonString();
    }

    @ApiOperation(value = "新增商品", notes = "新增商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "商品名称", required = true, dataType = "String", paramType =
                    "query"),
            @ApiImplicitParam(name = "categoryId", value = "商品分类ID", required = true, dataType = "int", paramType =
                    "query"),
            @ApiImplicitParam(name = "sellerId", value = "卖家编号，默认0，代表自有商品", required = true, dataType = "long",
                    paramType = "query", defaultValue = "0")
    })
    @PostMapping("goods")
    public String add(@RequestParam String name,
                      @RequestParam Integer categoryId,
                      @RequestParam Long sellerId) {
        // TODO 校验卖家ID是否存在
        MallCategory category = categoryService.selectById(categoryId);
        if (category == null){
            throw new BusinessException(ReturnCode.GOODS_CATEGORY_NULL);
        }
        MallGoods goods = new MallGoods();
        goods.setName(name);
        goods.setCategoryId(categoryId);
        goods.setSellerId(sellerId);
        boolean insert = goods.insert();
        if (!insert) {
            throw new BusinessException(ReturnCode.FAIL);
        }
        return new ReturnBean().toJsonString();
    }

    @ApiOperation(value = "修改商品", notes = "根据主键ID修改商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品ID", required = false, dataType = "long", paramType =
                    "path"),
            @ApiImplicitParam(name = "name", value = "商品名称", required = false, dataType = "String", paramType =
                    "query"),
            @ApiImplicitParam(name = "categoryId", value = "商品分类ID", required = false, dataType = "int", paramType =
                    "query"),
            @ApiImplicitParam(name = "sellerId", value = "卖家编号，默认0，代表自有商品", required = false, dataType = "long",
                    paramType = "query", defaultValue = "0"),
            @ApiImplicitParam(name = "spu", value = "SPU销量 +1", required = false, dataType = "long",
                    paramType = "query"),
            @ApiImplicitParam(name = "commentCount", value = "评论数量 +1", required = false, dataType = "long",
                    paramType = "query")
    })
    @PutMapping("goods/{id}")
    public String update(@PathVariable(value = "id") Long id,
                         @RequestParam(required = false) String name,
                         @RequestParam(required = false) Integer categoryId,
                         @RequestParam(required = false) Long sellerId,
                         @RequestParam(required = false) Long spu,
                         @RequestParam(required = false) Long commentCount) {
        MallGoods goods = new MallGoods();
        goods.setId(id);
        MallGoods select = goods.selectById();
        if (select == null) {
            return new BusinessException(ReturnCode.GOODS_NULL).toString();
        }
        if (categoryId != null){
            MallCategory category = categoryService.selectById(categoryId);
            if (category == null){
                throw new BusinessException(ReturnCode.GOODS_CATEGORY_NULL);
            }
        }
        // TODO 校验卖家ID是否存在
        if (spu != null){
            //SPU销量自增1
            spu += select.getSpu();
        }
        if (commentCount != null){
            //评论数自增1
            commentCount += select.getCommentCount();
        }
        goods.setName(name);
        goods.setCategoryId(categoryId);
        goods.setSellerId(sellerId);
        goods.setSpu(spu);
        goods.setCommentCount(commentCount);
        boolean update = goods.updateById();
        if (!update) {
            throw new BusinessException(ReturnCode.FAIL);
        }
        return new ReturnBean().toJsonString();
    }

    @ApiOperation(value = "删除商品", notes = "根据主键ID删除商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品ID", required = true, dataType = "long", paramType =
                    "path")
    })
    @DeleteMapping("goods/{id}")
    public String update(@PathVariable(value = "id") Long id) {
        MallGoods goods = new MallGoods();
        goods.setId(id);
        MallGoods select = goods.selectById();
        if (select == null) {
            return new BusinessException(ReturnCode.NULL_INFO).toString();
        }
        boolean delete = goods.deleteById();
        if (!delete) {
            throw new BusinessException(ReturnCode.FAIL);
        }
        return new ReturnBean().toJsonString();
    }

}

