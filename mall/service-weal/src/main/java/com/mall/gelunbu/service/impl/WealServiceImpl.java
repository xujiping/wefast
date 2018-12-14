package com.mall.gelunbu.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.common.base.constant.Constants;
import com.common.base.constant.ReturnBean;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.mall.gelunbu.entity.Weal;
import com.mall.gelunbu.mapper.WealMapper;
import com.mall.gelunbu.service.WealService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 福利表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-06-26
 */
@Service
public class WealServiceImpl extends ServiceImpl<WealMapper, Weal> implements WealService {

    @Override
    public Weal selectByClientListenId(String clientListenId) {
        if (StringUtils.isEmpty(clientListenId)) {
            throw new BusinessException(ReturnCode.PARAMS_ERROR);
        }
        Wrapper<Weal> wrapper = new EntityWrapper<>();
        wrapper.eq("client_listen_id", clientListenId);
        Weal weal = selectOne(wrapper);
        if (weal == null) {
            weal = new Weal();
            weal.setClientListenId(clientListenId);
            boolean insert = weal.insert();
            if (!insert){
                throw new BusinessException();
            }
            weal = weal.selectOne(wrapper);
        }
        return weal;
    }
}
