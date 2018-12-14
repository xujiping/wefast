package com.rb.cms.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.rb.cms.entity.MallGoods;
import com.rb.cms.entity.MallPropertyKey;
import com.rb.cms.entity.MallPropertyValue;
import com.rb.cms.entity.MallSku;
import com.rb.cms.entity.dto.SkuDto;
import com.rb.cms.service.MallGoodsService;
import com.rb.cms.service.MallPropertyKeyService;
import com.rb.cms.service.MallPropertyValueService;
import com.rb.cms.util.JsonListUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * SKU表（库存表） 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-06-19
 */
@Controller
@RequestMapping("/mallSku")
@Transactional(rollbackFor = BusinessException.class)
public class MallSkuController {

    @Autowired private MallGoodsService goodsService;
    @Autowired private MallPropertyKeyService propertyKeyService;
    @Autowired private MallPropertyValueService propertyValueService;

    @GetMapping("list")
    public String index() {
        return "sku/sku-list";
    }

    /**
     * 列表
     *
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @param seller
     * @param description
     * @return
     */
    @GetMapping("skus")
    @ResponseBody
    public Object users(@RequestParam(required = false, defaultValue = "1", value = "page") int page,
                        @RequestParam(required = false, defaultValue = "10", value = "rows") int rows,
                        @RequestParam(required = false, value = "sort") String sort,
                        @RequestParam(required = false, value = "order") String order,
                        @RequestParam(required = false, value = "seller") String seller,
                        @RequestParam(required = false, value = "description") String description) {
        MallSku sku = new MallSku();
        //条件
        Wrapper<MallSku> wrapper = new EntityWrapper<>();
        //分页
        Page<MallSku> objectPage = new Page<>(page, rows);
        List<MallSku> skuList = sku.selectPage(objectPage, wrapper).getRecords();
        List<SkuDto> skuDtoList = skuList.stream().map(this::wrapperSku).collect
                (Collectors.toList());
        int count = sku.selectCount(wrapper);
        return JsonListUtil.toJson(ReturnCode.SUCCESS.code(), ReturnCode.SUCCESS.msg(), (long) count, skuDtoList);
    }

    /**
     * 包装
     * @param sku
     * @return
     */
    private SkuDto wrapperSku(MallSku sku) {
        SkuDto skuDto = new SkuDto();
        if (sku == null){
            return skuDto;
        }
        BeanUtils.copyProperties(sku, skuDto);
        //商品信息
        MallGoods goods = goodsService.selectById(sku.getGoodsId());
        skuDto.setGoodsName(goods.getName());
        //属性
        StringBuffer skuName = new StringBuffer();
        String skuInfo = sku.getSku();
        if (StringUtils.isNotEmpty(skuInfo)){
            String[] properties = skuInfo.split("\\,");
            for (String property : properties) {
                String keyId = property.substring(0, property.indexOf(":"));
                String valueId = property.substring(property.indexOf(":") + 1);
                MallPropertyKey propertyKey = propertyKeyService.selectById(keyId);
                MallPropertyValue propertyValue = propertyValueService.selectById(valueId);
                skuName.append(propertyKey.getName() + "：" + propertyValue.getValue());
                skuName.append("，");
            }
            String substring = skuName.substring(0, skuName.length() - 1);
            skuDto.setSkuName(substring);
        }
        return skuDto;
    }
}

