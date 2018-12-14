package com.rb.cms.util.weixin.util;

import com.rb.cms.util.weixin.constant.WeixinConstant;
import com.rb.cms.util.weixin.model.Transfer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * 微信支付工具类
 *
 * @author xujiping
 * @date 2018/4/29 15:00
 */
public class WeixinPayUtil {

    public static final Logger LOG = LoggerFactory.getLogger(WeixinPayUtil.class);

    private static Transfer transfer = null;
    /**
     * 构造签名的map
     */
    private static SortedMap<Object, Object> parameters = new TreeMap<>();
    private static XStream xStream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("_-", "_")));

    public static void withdraw() {
        transfer = new Transfer();
        transfer.setMch_appid(WeixinConfigUtil.appid);
        transfer.setMchid(WeixinConfigUtil.mch_id);
        transfer.setNonce_str(RandCharsUtil.getRandomString(32));
        transfer.setPartner_trade_no(RandCharsUtil.generateOrderSN());
        transfer.setOpenid("o29Km06gD1RoyL2S9omJHRa0U0C4");
        transfer.setCheck_name("NO_CHECK");
        transfer.setAmount(1);
        transfer.setDesc("hello");
        transfer.setSpbill_create_ip("113.65.103.38");
        // 组合生成签名
        parameters.put("mch_appid", transfer.getMch_appid());
        parameters.put("mchid", transfer.getMchid());
        parameters.put("nonce_str", transfer.getNonce_str());
        parameters.put("partner_trade_no", transfer.getPartner_trade_no());
        parameters.put("openid", transfer.getOpenid());
        parameters.put("check_name", transfer.getCheck_name());
        parameters.put("amount", transfer.getAmount());
        parameters.put("desc", transfer.getDesc());
        parameters.put("spbill_create_ip", transfer.getSpbill_create_ip());
        transfer.setSign(WXSignUtil.createSign("ISO8859-1", parameters));
        String xmlInfo = transferXml(transfer);
        try {
            CloseableHttpResponse response = HttpUtil.Post(WeixinConstant.WITHDRAW_URL, xmlInfo, true);
            String transfersXml = EntityUtils.toString(response.getEntity(), "utf-8");
            Map<String, String> transferMap = parseRefundXml(transfersXml);
            if (transferMap.size() > 0) {
                if (transferMap.get("returnCode").equals("SUCCESS") && transferMap.get("resultCode").equals
                        ("SUCCESS")) {
                    // TODO 成功需要进行的逻辑操作
                }
            }
            System.out.println("成功");
        } catch (Exception e) {
            LOG.error("微信转账错误", e);
            // TODO 转账错误
        }
        // TODO 写入结果到数据库
    }

    /**
     * 构造企业付款xml参数
     *
     * @return
     */
    private static String transferXml(Transfer transfer) {
        xStream.autodetectAnnotations(true);
        xStream.alias("xml", Transfer.class);
        return xStream.toXML(transfer);
    }

    /**
     * 解析申请退款之后微信返回的值并进行存库操作
     *
     * @throws IOException
     * @throws JDOMException
     */
    public static Map<String, String> parseRefundXml(String refundXml) {
        SAXReader reader = new SAXReader();
        Map<String, String> map = new HashMap<>();
        try {
            Document document = DocumentHelper.parseText(refundXml);
            //获取根节点
            Element root = document.getRootElement();
            Iterator it = root.elementIterator();
            String returnCode = root.element("return_code").getText();
            String returnMsg = root.element("return_msg").getText();
            String resultCode = root.element("result_code").getText();
            if ("SUCCESS".equals(returnCode)) {
                String mchAppid = root.element("mch_appid").getText();
                String mchid = root.element("mchid").getText();
                String deviceInfo = root.element("device_info").getText();
                String nonceStr = root.element("nonce_str").getText();
                String partnerTradeNo = root.element("partner_trade_no").getText();
                String paymentNo = root.element("payment_no").getText();
                String paymentTime = root.element("payment_time").getText();
                map.put("mchAppid", mchAppid);
                map.put("mchid", mchid);
                map.put("deviceInfo", deviceInfo);
                map.put("nonceStr", nonceStr);
                map.put("partnerTradeNo", partnerTradeNo);
                map.put("paymentNo", paymentNo);
                map.put("paymentTime", paymentTime);
            } else {
                String errCode = root.element("err_code").getText();
                String errCodeDes = root.element("err_code_des").getText();
                map.put("errCode", errCode);
                map.put("errCodeDes", errCodeDes);
            }
            map.put("returnCode", returnCode);
            map.put("returnMsg", returnMsg);
            map.put("resultCode", resultCode);
        } catch (DocumentException e) {
            LOG.error("微信提现返回结果解析错误", e);
            return null;
        }
        return map;
    }

    public static void main(String[] args) {
//        String test = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[]]></return_msg" +
//                "><mch_appid><![CDATA[wxec38b8ff840bd989]]></mch_appid><mchid><![CDATA[10013274]]></mchid" +
//                "><device_info><![CDATA[]]></device_info><nonce_str><![CDATA[lxuDzMnRjpcXzxLx0q]]></nonce_str" +
//                "><result_code><![CDATA[SUCCESS]]></result_code><partner_trade_no><![CDATA
// [10013574201505191526582441" +
//                "]]></partner_trade_no><payment_no><![CDATA[1000018301201505190181489473]]></payment_no
// ><payment_time" +
//                "><![CDATA[2015-05-19 15：26：59]]></payment_time></xml>";
//        Map<String, String> map = parseRefundXml(test);
        withdraw();
    }
}
