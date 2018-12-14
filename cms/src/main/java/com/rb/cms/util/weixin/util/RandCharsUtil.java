package com.rb.cms.util.weixin.util;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * 产生随机串
 * @author xujiping
 * @date 2018/4/29 15:10
 */
public class RandCharsUtil {

    private static SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        int number = 0;
        for (int i = 0; i < length; i++) {
            number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 针对微信支付生成商户订单号，为了避免微信商户订单号重复（下单单位支付），
     *
     * @return
     */
    public static String generateOrderSN() {
        StringBuffer orderSNBuffer = new StringBuffer();
        orderSNBuffer.append(System.currentTimeMillis());
        orderSNBuffer.append(getRandomString(7));
        return orderSNBuffer.toString();
    }
}
