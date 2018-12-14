package com.mall.goods.service;

import com.mall.goods.hystrix.SchedualServiceHiHystric;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 用户福利服务
 *
 * @author xujiping
 * @date 2018/6/29 11:54
 */
@FeignClient(value = "service-weal", fallback = SchedualServiceHiHystric.class)
public interface WealService {

    /**
     * 获取用户福利信息
     * @param clientListenId
     * @return
     */
    @GetMapping("/weal/{clientListenId}")
    String info(@PathVariable(value = "clientListenId") String clientListenId);
}
