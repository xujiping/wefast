package com.rb.cms.util.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransOrderQueryRequest;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipayFundTransOrderQueryResponse;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * 支付宝工具类
 *
 * @author xujiping
 * @date 2018/4/29 11:03
 */
public class AlipayUtil {

    private static final Logger LOG = LoggerFactory.getLogger(AlipayUtil.class);

    /**
     * 支付宝网关
     */
    public static final String SERVER_URL = "https://openapi.alipay.com/gateway.do";
    /**
     * 听多多APPID
     */
    public static final String APP_ID = "2018073060795792";
    /**
     * 应用私钥
     */
    public static final String PRIVATE_KEY =
            "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQClttyaezeR6CtpcSyUvrtcjBVmYD5aJmVJAGr" +
                    "/Rd2K8sd52Y3quhJBYc2zvSll3SUN8IuRrNPUg9hemRH7BqRcel11pjNEbCBIBvjt" +
                    "+3j91y7qSFRCJRv1mDranBLoJpS04e5KIT6V3Y7FygwgjjjJAx3UYXIDr1/g8Gzbg9Zqj2MGiON1r1ImtqIUlIxxORzDdnvf" +
                    "/cVuYdYfb63D63Mq/qFqtaPbONBDZfmU+OcT9bdocqb9ndUTbjtDCDFmR7QDWx9yEJHMKpQUVs12p2j3JJEvn" +
                    "+AiMxo6qOkYZlcoxLSCJnp8FIDSxBIe8FyjXBjJhJ3No+12" +
                    "+xknQdeounJdAgMBAAECggEBAIymtX0lAhiET3xyg51IeexmNa+sFqAboLIiea5rVUufHrIMdkj5X49A" +
                    "/QAGmQGNfuri9QNTBoSE13wLPdirXYvkjDjUNQVpJMkdqeEMJTiGkJpng6SUrLN6XtG30NBcFrGkZcVjjCRVZd34zOxA6m" +
                    "+qhoIBp2z7SEy6pBp5Pt83ruZKww3C7brsemIB8Hqs1YdohN5d2GLcjZZQkh8wuK/CWFkD0qEfi0adhm1832UEAYOlRWWLan" +
                    "+kZzCbmO4FClHtB/ccuIoGBgVkw1EyKePyiZiyMFhPaqwPd4uXFlr8cNp+ave9F2Twz" +
                    "+dX6jqVU5LwIuD5qHchO67JmDLs7wECgYEA5aOiPZjPGFs7zX8CHDNwD9qXOxtUBS4" +
                    "+UbF7TWFxcGKtNZe0YudvV1mspTvWMehleNY+68s7ow5KzmwlOLniiQhuaKxRvftbdse237ajd8zZyKyx1W1Z3yySQDRnGf" +
                    "/1pQ6aYoAMRDAxOYonrslvu3lzGi5FRiUr57hszL0LtEECgYEAuLyttj" +
                    "+9ztIlwtTHHj2DK0F5bVBbPgy6MVwL6Q8HhXYJ91lSGTLFxl455J+8vahAE0" +
                    "/D2Iccn35TQKKADYTNXHfiHZAfGUwFvQJZnMAtyjYV8T2BhUj1jGNrCzLujlaOXNotFTCyBuYpSPVI3z" +
                    "/epipFgIGdHixvlj2PK+EpRx0CgYB8fSDO07AxPARHerriHuB5N78UQ9RgmpkeIGD/qfudOWE81Gjt1Rj++ZYmXTHZDGs1" +
                    "/q9NlHdYBb93+BiVj+/G6dMw23nUzBbhZZM15ATqRRsrNrORSB6YZclDFSVvatr4B9qZ22DK9nU5BDRPBNSVhVlor" +
                    "/zN8gjDLa+Nrk5SAQKBgGE3KVMXCBOmkyiMQJyxUi2ZLwVPnDRzDiibf8kNl6+sBmKcJpAMH/fb7itZxEEqIU4IU8" +
                    "/GilGEcNs6qJh0on1euSdFiJgPYZWtfur2VyJqg/GHpHk3g8B1MQebrd5JvnEufzr/fTAUPvWd3tNdERXweNK" +
                    "/YqdWSwKNMqKswsQhAoGBAIzRs+iID27eyek7WhO1gnw+YL49romtYBgj6E0NalUuSOG1PmFH7J71TRqarKpe6tfi" +
                    "/4oIAhoE2WcuAotznIlGngr/f1ShoezXF6YYuYG5oJqvrYEGFR" +
                    "+/MjAq00rUhRCblf0jXc8pY5NoEUu7QluVHxEMGegEUiutT2+kXYQb";
    /**
     * 应用公钥
     */
    public static final String ALIPAY_PUBLIC_KEY =
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAho6CSfNQgniwveswIb8zeY" +
                    "+W7PqzCGUqQr1IYq4vmDjMTgM0jQSjujOqDMi3GMWawo+c/XT86q/Hlkx24CAAuTPScF+g/pBCMP" +
                    "+J0kNjG8t7uKUPmoMHuc2NUD7VcnzgCxoIEIZurb74qrEElJ1taOS0vRjLcn/f8O90WB1ZmMbK0cBt+Ip9ObPgCK" +
                    "+Tkt037EcuuLX0TYOChI1IH6mdDzTBUo3dcSBwj9H3UK7TDpN9+m5ivoo364TXgxsTCgqErkjiLHgGs6J" +
                    "/gA41wzPf4ZvjR9c58lNJDwKu8/ZhnXkfgPpPWiQNmIwfV1o29Mgx9nt+69bfCjEBf1ktMDCF6QIDAQAB";
    /**
     * 签名算法类型
     */
    public static final String SIGN_TYPE = "RSA2";

    /**
     * 支付方名称
     */
    public static final String PAYER_SHOW_NAME = "听多多零钱提现";

    public static final String CHARSET = "UTF-8";

    /**
     * 格式类型
     */
    public static final String FORMAT = "json";

    /**
     * 支付宝转账提现接口
     *
     * @param clientListenId 用户ID
     * @param alipayUserid   支付宝唯一用户号
     * @param alipayLoginId  支付宝登录号,用户号/登录号二选一，如果都存在，则使用登录号
     * @param amount         转账金额，单位：元。只支持2位小数，小数点前最大支持13位，金额必须大于等于0.1元
     * @param realName       收款方真实姓名（最长支持100个英文/50个汉字）
     * @param remark         转账备注（支持200个英文/100个汉字）。当付款方为企业账户，且转账金额达到（大于等于）50000元，remark不能为空。
     *                       收款方可见，会展示在收款用户的收支详情中。
     */
    public static AlipayFundTransToaccountTransferResponse transfer(String clientListenId, String alipayUserid, String alipayLoginId, String amount, String
            realName, String remark) {
        String outBizNo = generateBizNo();
        String payeeType = null;
        String account = null;
        if (!StringUtils.isEmpty(alipayUserid)) {
            payeeType = "ALIPAY_USERID";
            account = alipayUserid;
        }
        if (!StringUtils.isEmpty(alipayLoginId)) {
            payeeType = "ALIPAY_LOGONID";
            account = alipayLoginId;
        }
        AlipayClient alipayClient = new DefaultAlipayClient(SERVER_URL, APP_ID, PRIVATE_KEY, "json", CHARSET,
                ALIPAY_PUBLIC_KEY, SIGN_TYPE);
        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
        request.setBizContent("{" +
                "\"out_biz_no\":\"" + outBizNo + "\"," +
                "\"payee_type\":\"" + payeeType + "\"," +
                "\"payee_account\":\"" + account + "\"," +
                "\"amount\":\"" + amount + "\"," +
                "\"payer_show_name\":\"" + PAYER_SHOW_NAME + "\"," +
//                "\"payee_real_name\":\"" + realName + "\"," +
                "\"remark\":\"" + remark + "\"," +
                "}");
        AlipayFundTransToaccountTransferResponse response = null;
        try {
            response = alipayClient.execute(request);
            LOG.debug("支付宝转账返回：\n" + response.getBody());
        } catch (AlipayApiException e) {
            LOG.error("支付宝转账异常", e);
        }
        if (response.isSuccess()) {
            LOG.debug("支付宝转账成功");
            //  TODO 写入数据库
        } else {
            LOG.debug("支付宝转账失败");
            // TODO 发送消息通知，说明失败原因
        }
        return response;
    }

    /**
     * 生成唯一订单号
     *
     * @return
     */
    private static String generateBizNo() {
        //最大支持1-9个集群机器部署
        int machineId = 1;
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        //有可能是负数
        if (hashCodeV < 0) {
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return machineId + String.format("%015d", hashCodeV);
    }

    /**
     * 沙箱账号转账测试
     */
    private static void testTransfer() {
        //沙箱网关
        String serverUrl = "https://openapi.alipaydev.com/gateway.do";
        //应用ID
        String appId = "2016091800540515";
        //应用私钥
        String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQClttyaezeR6CtpcSyUvrtcjBVmYD5aJmVJAGr" +
                "/Rd2K8sd52Y3quhJBYc2zvSll3SUN8IuRrNPUg9hemRH7BqRcel11pjNEbCBIBvjt" +
                "+3j91y7qSFRCJRv1mDranBLoJpS04e5KIT6V3Y7FygwgjjjJAx3UYXIDr1/g8Gzbg9Zqj2MGiON1r1ImtqIUlIxxORzDdnvf" +
                "/cVuYdYfb63D63Mq/qFqtaPbONBDZfmU+OcT9bdocqb9ndUTbjtDCDFmR7QDWx9yEJHMKpQUVs12p2j3JJEvn" +
                "+AiMxo6qOkYZlcoxLSCJnp8FIDSxBIe8FyjXBjJhJ3No+12+xknQdeounJdAgMBAAECggEBAIymtX0lAhiET3xyg51IeexmNa" +
                "+sFqAboLIiea5rVUufHrIMdkj5X49A" +
                "/QAGmQGNfuri9QNTBoSE13wLPdirXYvkjDjUNQVpJMkdqeEMJTiGkJpng6SUrLN6XtG30NBcFrGkZcVjjCRVZd34zOxA6m" +
                "+qhoIBp2z7SEy6pBp5Pt83ruZKww3C7brsemIB8Hqs1YdohN5d2GLcjZZQkh8wuK/CWFkD0qEfi0adhm1832UEAYOlRWWLan" +
                "+kZzCbmO4FClHtB/ccuIoGBgVkw1EyKePyiZiyMFhPaqwPd4uXFlr8cNp+ave9F2Twz" +
                "+dX6jqVU5LwIuD5qHchO67JmDLs7wECgYEA5aOiPZjPGFs7zX8CHDNwD9qXOxtUBS4" +
                "+UbF7TWFxcGKtNZe0YudvV1mspTvWMehleNY+68s7ow5KzmwlOLniiQhuaKxRvftbdse237ajd8zZyKyx1W1Z3yySQDRnGf" +
                "/1pQ6aYoAMRDAxOYonrslvu3lzGi5FRiUr57hszL0LtEECgYEAuLyttj" +
                "+9ztIlwtTHHj2DK0F5bVBbPgy6MVwL6Q8HhXYJ91lSGTLFxl455J+8vahAE0" +
                "/D2Iccn35TQKKADYTNXHfiHZAfGUwFvQJZnMAtyjYV8T2BhUj1jGNrCzLujlaOXNotFTCyBuYpSPVI3z/epipFgIGdHixvlj2PK" +
                "+EpRx0CgYB8fSDO07AxPARHerriHuB5N78UQ9RgmpkeIGD/qfudOWE81Gjt1Rj++ZYmXTHZDGs1/q9NlHdYBb93+BiVj" +
                "+/G6dMw23nUzBbhZZM15ATqRRsrNrORSB6YZclDFSVvatr4B9qZ22DK9nU5BDRPBNSVhVlor/zN8gjDLa" +
                "+Nrk5SAQKBgGE3KVMXCBOmkyiMQJyxUi2ZLwVPnDRzDiibf8kNl6+sBmKcJpAMH/fb7itZxEEqIU4IU8" +
                "/GilGEcNs6qJh0on1euSdFiJgPYZWtfur2VyJqg/GHpHk3g8B1MQebrd5JvnEufzr/fTAUPvWd3tNdERXweNK" +
                "/YqdWSwKNMqKswsQhAoGBAIzRs+iID27eyek7WhO1gnw+YL49romtYBgj6E0NalUuSOG1PmFH7J71TRqarKpe6tfi" +
                "/4oIAhoE2WcuAotznIlGngr/f1ShoezXF6YYuYG5oJqvrYEGFR+/MjAq00rUhRCblf0jXc8pY5NoEUu7QluVHxEMGegEUiutT2" +
                "+kXYQb";
        //支付宝公钥
        String alipayPublicKey =
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAt" +
                        "+z3gkENx54bxjUmcqafufO0l9waW200fbzb8Wpfmtc7EH5822EGiyINGT7ZM5eUGUhMxMrHch" +
                        "+/VHBcJvwgeLeW3HQKaUtGjRjdtHzYMWBk/+br1FPYtqeAD+smo5rwJhqYHsR+vNSu5rrDqQ5EAJLZEA5bZIL0LV" +
                        "/hsNbALU1vyWWmTlVZnGys56PWgb1NPU9HJQQ3BFSpqXrv3OJ4+CB8GN6RYSMu90hf8cDUF6rr3Bl3ULT1g+GA667P" +
                        "+kKC7qB64whWLjrbz044rPeFG+iubFvPaCpFMAxH59w88XNsyz9opUDB3gei8T7L46MMUYj7te/u3vWNwyeM9" +
                        "+MzwwIDAQAB";
        //签名算法类型
        String signType = "RSA2";
        //唯一订单号，自己生成
        String outBizNo = generateBizNo();
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, "json", CHARSET,
                alipayPublicKey, signType);
        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
        request.setBizContent("{" +
                "\"out_biz_no\":\"" + outBizNo + "\"," +
                "\"payee_type\":\"ALIPAY_LOGONID\"," +
                "\"payee_account\":\"glibnl6360@sandbox.com\"," +
                "\"amount\":\"50.00\"," +
                "\"payer_show_name\":\"听多多提现\"," +
                "\"payee_real_name\":\"沙箱环境\"," +
                "\"remark\":\"转账备注\"," +
                "}");
        AlipayFundTransToaccountTransferResponse response = null;
        try {
            response = alipayClient.execute(request);
            System.out.println("转账接口返回：\n" + response.getBody());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if (response.isSuccess()) {
            System.out.println("转账调用成功");
            //调用查询
            testQuery(alipayClient, outBizNo, response.getOrderId());
        } else {
            System.out.println("转账调用失败");
        }
    }

    private static void testQuery(AlipayClient alipayClient, String outBizNo, String orderId) {
        AlipayFundTransOrderQueryRequest request = new AlipayFundTransOrderQueryRequest();
        request.setBizContent("{" +
                "    \"out_biz_no\":\"" + outBizNo + "\"," +
                "    \"order_id\":\"" + orderId + "\"" +
                "  }");
        AlipayFundTransOrderQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
            System.out.println("查询接口返回：\n" + response.getBody());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if (response.isSuccess()) {
            System.out.println("查询调用成功");
        } else {
            System.out.println("查询调用失败");
        }
    }

    /**
     * 支付宝会员授权信息查询接口
     *
     * @param accessToken 支付宝访问令牌
     * @return
     */
    public static AlipayUserInfoShareResponse infoShare(String accessToken) {
        DefaultAlipayClient alipayClient = new DefaultAlipayClient(SERVER_URL, APP_ID, PRIVATE_KEY, FORMAT, CHARSET,
                ALIPAY_PUBLIC_KEY, SIGN_TYPE);
        AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();
        AlipayUserInfoShareResponse response = null;
        try {
            response = alipayClient.execute(request, accessToken);
        } catch (AlipayApiException e) {
            LOG.error("Alipay【支付宝会员授权信息查询接口】错误-", e);
            e.printStackTrace();
        }
        return response;
    }

    public static void main(String[] args) {
        testTransfer();
    }
}
