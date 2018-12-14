package com.rb.cms.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.base.constant.ReturnBean;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.rb.cms.entity.MallCategory;
import com.rb.cms.entity.MallGoods;
import com.rb.cms.entity.MallGoodsDetail;
import com.rb.cms.entity.dto.GoodsDto;
import com.rb.cms.service.MallCategoryService;
import com.rb.cms.service.MallGoodsDetailService;
import com.rb.cms.service.MallGoodsService;
import com.rb.cms.util.DateUtils;
import com.rb.cms.util.JsonListUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-06-19
 */
@Controller
@RequestMapping("/mallGoods")
@Transactional(rollbackFor = BusinessException.class)
public class MallGoodsController {

    @Autowired
    private MallGoodsService goodsService;
    @Autowired
    private MallGoodsDetailService goodsDetailService;
    @Autowired
    private MallCategoryService categoryService;

    @GetMapping("list")
    public String index() {
        return "goods/goods-list";
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
    @GetMapping("goods")
    @ResponseBody
    public Object users(@RequestParam(required = false, defaultValue = "1", value = "page") int page,
                        @RequestParam(required = false, defaultValue = "10", value = "rows") int rows,
                        @RequestParam(required = false, value = "sort") String sort,
                        @RequestParam(required = false, value = "order") String order,
                        @RequestParam(required = false, value = "name") String name,
                        @RequestParam(required = false, value = "seller") String seller,
                        @RequestParam(required = false, value = "description") String description) {
        MallGoods mallGoods = new MallGoods();
        //条件
        Wrapper<MallGoods> wrapper = new EntityWrapper<>();
        if (StringUtils.isNotEmpty(name)) {
            wrapper.like("name", name);
        }
        //分页
        Page<MallGoods> objectPage = new Page<>(page, rows);
        List<MallGoods> goodsList = mallGoods.selectPage(objectPage, wrapper).getRecords();
        List<GoodsDto> goodsDtoList = goodsList.stream().map(mallGoods1 -> wrapperGoods(mallGoods1)).collect
                (Collectors.toList());
        int count = mallGoods.selectCount(wrapper);
        return JsonListUtil.toJson(ReturnCode.SUCCESS.code(), ReturnCode.SUCCESS.msg(), (long) count, goodsDtoList);
    }

    private GoodsDto wrapperGoods(MallGoods mallGoods) {
        GoodsDto goodsDto = new GoodsDto();
        if (mallGoods == null) {
            return goodsDto;
        }
        BeanUtils.copyProperties(mallGoods, goodsDto);
        MallCategory mallCategory = categoryService.selectById(mallGoods.getCategoryId());
        goodsDto.setCategory(mallCategory.getName());
        return goodsDto;
    }

    @GetMapping("edit/{id}")
    public String editPage(@PathVariable(value = "id") String id, Model model) {
        MallGoods goods = goodsService.selectById(id);
        MallGoodsDetail goodsDetail = goodsDetailService.selectById(id);
        model.addAttribute("goods", goods);
        model.addAttribute("goodsDetail", goodsDetail);
        return "goods/goods-edit";
    }

    /**
     * 修改
     *
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    @ResponseBody
    public ReturnBean edit(@PathVariable(value = "id") String id, String name, String detail) {
        Date date = new Date();
        MallGoods goods = new MallGoods();
        goods.setId(Long.parseLong(id));
        goods.setName(name);
        boolean update = goods.updateById();
        if (!update) {
            return new ReturnBean(ReturnCode.FAIL, null);
        }
        //更新商品详情
        MallGoodsDetail goodsDetail = new MallGoodsDetail();
        goodsDetail.setId(Long.parseLong(id));
        goodsDetail.setName(name);
        goodsDetail.setDescription(detail);
        goodsDetail.setUpdateTime(date);
        boolean update1 = goodsDetail.updateById();
        if (!update1) {
            return new ReturnBean(ReturnCode.FAIL, null);
        }
        return new ReturnBean();
    }

    /**
     * 商品详情预览页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("detail/{id}")
    public String detailPage(@PathVariable(value = "id") String id, Model model){
        MallGoodsDetail goodsDetail = goodsDetailService.selectById(id);
        model.addAttribute("goodsDetail", goodsDetail);
        return "goods/goods-detail";
    }

    @GetMapping("")
    public String addPage(){
        return "goods/goods-add";
    }

}

