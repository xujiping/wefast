package com.rb.cms.util;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 极光推送
 *
 * @author xujiping
 * @date 2018/5/14 15:35
 */
public class JgUtil {

    static final Logger LOG = LoggerFactory.getLogger(JgUtil.class);

    private static final String APP_KEY = "b2d307361e74ac227bf9b35d";
    private static final String MASTER_SECRET = "fb95ba217f851e1a38478a70";

    /**
     * 推送消息
     *
     * @param type           类型：notice/message，默认为message
     * @param title          标题
     * @param content        消息体
     * @param clientListenId 如果为null，则发送所有
     */
    public static void sendMessageToClient(String type, String title, String content, String clientListenId) {
        ClientConfig clientConfig = ClientConfig.getInstance();
        final JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, clientConfig);
        final PushPayload payload = buildPushObjectAndroidAndIos(type, title, content, clientListenId);
        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("Got result - " + result);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
            LOG.error("Sendno: " + payload.getSendno());
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
            LOG.error("Sendno: " + payload.getSendno());
        }
    }

    /**
     * 构建推送对象：所有平台
     *
     * @param title          标题
     * @param content        内容
     * @param clientListenId 用户ID
     * @return
     */
    private static PushPayload buildPushObjectAndroidAndIos(String type, String title, String content, String
            clientListenId) {
        Map<String, String> extras = Maps.newHashMapWithExpectedSize(1);
        Audience audience = Audience.all();
        if (clientListenId != null) {
            audience = Audience.alias(clientListenId);
        }
        extras.put("type", type);
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(audience)
                .setNotification(Notification.newBuilder()
                        .setAlert(content)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle(title)
                                .addExtras(extras).build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .incrBadge(1)
                                .addExtra("extra_key", "extra_value").build())
                        .build())
                .setMessage(Message.content(content))
                //设置生产环境，默认环境是开发
                //.setOptions(Options.newBuilder().setApnsProduction(true).build())
                .build();
    }

    public static void main(String[] args) {
        sendMessageToClient("notice", "消息标题", "测试", null);
    }
}
