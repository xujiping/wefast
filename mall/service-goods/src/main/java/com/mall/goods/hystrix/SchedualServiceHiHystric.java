package com.mall.goods.hystrix;

import com.common.base.constant.ReturnBean;
import com.common.base.constant.ReturnCode;
import com.mall.goods.service.WealService;
import org.springframework.stereotype.Component;

/**
 * 福利接口断路器
 * @author xujiping
 * @date 2018/6/29 14:40
 */
@Component
public class SchedualServiceHiHystric implements WealService {

    @Override
    public String info(String clientListenId) {
        return new ReturnBean(ReturnCode.FAIL, "用户福利服务不可用").toJsonString();
    }
}
