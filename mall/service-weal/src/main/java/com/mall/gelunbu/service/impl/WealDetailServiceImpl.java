package com.mall.gelunbu.service.impl;

import com.common.base.constant.Constants;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.mall.gelunbu.entity.Weal;
import com.mall.gelunbu.entity.WealDetail;
import com.mall.gelunbu.mapper.WealDetailMapper;
import com.mall.gelunbu.service.WealDetailService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mall.gelunbu.service.WealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 * 福利详情表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-06-26
 */
@Service
public class WealDetailServiceImpl extends ServiceImpl<WealDetailMapper, WealDetail> implements WealDetailService {

    @Autowired
    private WealService wealService;

    @Override
    public boolean updateDetailAndWeal(WealDetail wealDetail, Weal weal) {
        if (wealDetail == null || weal == null){
            throw new BusinessException(ReturnCode.PARAMS_ERROR);
        }
        //新增福利详情
        boolean insert = insert(wealDetail);
        if (!insert) {
            throw new BusinessException();
        }
        Integer income = wealDetail.getIncome();
        Integer type = wealDetail.getType();
        Double size = wealDetail.getSize();
        if (type == Constants.WEAL_DETAIL_TYPE_GOLD) {
            if (income == Constants.WEAL_DETAIL_INCOME) {
                weal.setCurrentGold(weal.getCurrentGold() + size);
                weal.setTodayGold(weal.getTodayGold() + size);
            } else {
                weal.setCurrentGold(weal.getCurrentGold() - size);
            }
        }
        if (type == Constants.WEAL_DETAIL_TYPE_COIN) {
            if (income == Constants.WEAL_DETAIL_INCOME) {
                weal.setAllCoin(weal.getAllCoin() + size);
                weal.setCurrentCoin(weal.getCurrentCoin() + size);
                weal.setTodayCoin(weal.getTodayCoin() + size);
            } else {
                weal.setCurrentCoin(weal.getCurrentCoin() - size);
            }
        }
        if (type == Constants.WEAL_DETAIL_TYPE_BEAN) {
            double newCurrentBean;
            if (income == Constants.WEAL_DETAIL_INCOME) {
                newCurrentBean = weal.getCurrentBean().doubleValue() + size;
            } else {
                newCurrentBean = weal.getCurrentBean().doubleValue() - size;
            }
            weal.setCurrentBean(new BigDecimal(newCurrentBean));
        }
        //更新福利信息
        boolean update = wealService.updateById(weal);
        if (!update) {
            throw new BusinessException();
        }
        return true;
    }
}
