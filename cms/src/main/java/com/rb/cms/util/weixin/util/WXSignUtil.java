package com.rb.cms.util.weixin.util;

import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * 微信签名工具类
 * @author xujiping
 * @date 2018/4/29 15:11
 */
public class WXSignUtil {

    /**
     * 微信支付签名算法sign
     * @param characterEncoding
     * @param parameters
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String createSign(String characterEncoding,SortedMap<Object,Object> parameters){
        StringBuffer sb = new StringBuffer();
        //所有参与传参的参数按照accsii排序（升序）
        Set es = parameters.entrySet();
        for (Object e : es) {
            Map.Entry entry = (Map.Entry) e;
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + WeixinConfigUtil.cer_key);
        String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
        return sign;
    }
}
