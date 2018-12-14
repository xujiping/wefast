package com.rb.cms.util.weixin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author xujiping
 * @date 2018/4/29 15:07
 */
public class WeixinConfigUtil {

    private static final Logger log = LoggerFactory.getLogger(WeixinConfigUtil.class);
    public static String appid;
    public static String mch_id;
    public static String notify_url;
    public static String order_notify_url;
    public static String doctor_notify_url;
    public static String cer_path;
    public static String cer_key;
    static {
        try{
            InputStream is = WeixinConfigUtil.class.getResourceAsStream("/weixin.properties");
            Properties properties = new Properties();
            properties.load(is);
            appid = properties.getProperty("weixin.appid");
            mch_id = properties.getProperty("weixin.mch_id");
            notify_url = properties.getProperty("weixin.notify_url");
            order_notify_url = properties.getProperty("weixin.order_notify_url");
            doctor_notify_url = properties.getProperty("weixin.doctor_notify_url");
            cer_path = properties.getProperty("weixin.cer_path");
            cer_key = properties.getProperty("weixin.cer_key");
        }catch(Exception ex){
            log.debug("加载配置文件："+ex.getMessage());
        }
    }
}
