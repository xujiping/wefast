package com.frontend.media.service;

import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.netflix.discovery.converters.Auto;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 短信工具类
 *
 * @author xujiping
 * @date 2018/8/16 14:32
 */
@Service
public class SmsService {

    private final String URL = "http://gw.api.taobao.com/router/rest";

    private final String APPKEY = "23716073";

    private final String SECRET = "d52192347a051279af98a2fc2316965b";

    @Autowired
    private RedisService redisService;

    /**
     * 发送短信验证码
     *
     * @param phone 手机号
     * @return
     */
    public String sendCode(String phone) {
        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("123456");
        req.setSmsType("normal");
        req.setSmsFreeSignName("听多多验证码");
        int code = (int) (Math.random() * (9999 - 1000 + 1)) + 1000;
        req.setSmsParamString("{\"code\":\"" + code + "\",\"product\":\"听多多\"}");
        req.setRecNum(phone);
        req.setSmsTemplateCode("SMS_57000235");
        AlibabaAliqinFcSmsNumSendResponse rsp;
        //校验码有效期5分钟
        redisService.set(phone, code, 300L);
        try {
            rsp = client.execute(req);
        } catch (ApiException e) {
            throw new BusinessException(ReturnCode.SMS_SEND_ERROR);
        }
        return rsp.getBody();
    }

}
