package com.common.base.constant;

import java.io.File;

/**
 * 公共静态类
 *
 * @author xujiping
 * @date 2018/6/8 16:59
 */
public class Constants {

    public static final String RESOURCE_SERVER_NAME = "chapter17-server";
    public static final String INVALID_CLIENT_DESCRIPTION = "客户端验证失败，如错误的client_id/client_secret。";

    /**
     * 路径分隔符
     */
//    public static final String PATH_SEPARATOR = File.separator;
    public static final String PATH_SEPARATOR = "/";

    /**
     * auth code 超时时间（毫秒）
     */
    public static final long CODE_EXPIRE_IN = 360000;

    /**
     * auth code redis key 前缀
     */
    public static final String CODE_REDIS_KEY_PREFIX = "code_";

    /**
     * auth token 超时时间（毫秒）
     */
    public static final long TOKEN_EXPIRE_IN = 360000;
    public static final String TOKEN_REDIS_KEY_PREFIX = "token_";

    public static final String SALT = "123456";

    /**
     * 用户福利详情：1收入 2支出
     */
    public static final int WEAL_DETAIL_INCOME = 1;
    public static final int WEAL_DETAIL_EXPEND = 2;

    /**
     * 用户福利详情类别：1金币 2零钱 3福豆
     */
    public static final int WEAL_DETAIL_TYPE_GOLD = 1;
    public static final int WEAL_DETAIL_TYPE_COIN = 2;
    public static final int WEAL_DETAIL_TYPE_BEAN = 3;

    /**
     * 0不可用,1未付款,2已付款,3未发货,4已发货,5已签收,6退货申请,7退货中,8已退货,9取消交易
     */
    public static final int ORDER_STATUS_DISABLED = 0;
    public static final int ORDER_STATUS_NOT_PAY = 1;
    public static final int ORDER_STATUS_PAYED = 2;
    public static final int ORDER_STATUS_NOT_YET_SHIPPED = 3;
    public static final int ORDER_STATUS_DELIVERED = 4;
    public static final int ORDER_STATUS_SIGNED = 5;
    public static final int ORDER_STATUS_RETURN_REQUEST = 6;
    public static final int ORDER_STATUS_RETURNING = 7;
    public static final int ORDER_STATUS_RETURN_FINISH = 8;
    public static final int ORDER_STATUS_CANCEL = 9;
    public static final int ORDER_STATUS_FINISH = 10;

    /**
     * 订单结算状态：1货到付款、2分期付款、在线结算
     */
    public static final int ORDER_SETTLEMENT_STATUS_PAY_ON_DELIVERY = 1;
    public static final int ORDER_SETTLEMENT_STATUS_INSTALLMENT = 2;
    public static final int ORDER_SETTLEMENT_STATUS_FINISHED = 3;

    /**
     * 状态（normal：正常、audit：待审核、pass：通过、failed：未通过、block：冻结）
     */
    public static final String STAT_ALL = "all";
    public static final String STAT_NORMAL = "normal";
    public static final String STAT_AUDIT = "audit";
    public static final String STAT_PASS = "pass";
    public static final String STAT_FAILED = "failed";
    public static final String STAT_BLOCK = "block";

    /**
     * redis专辑信息
     */
    public static final String REDIS_CLIENT_RELEASE = "client_release";

    /**
     * 头像文件存储所属子文件夹
     */
    public static final String HEADER_FILE_PATH = "/header/";

    /**
     * 组织类型（个人、媒体、国家机构、企业、其他组织）
     */
    public static final String ORIGIN_TYPE_PERSON = "个人";
    public static final String ORIGIN_TYPE_MEDIA = "媒体";
    public static final String ORIGIN_TYPE_COUNTRY = "国家机构";
    public static final String ORIGIN_TYPE_COMPANY = "企业";
    public static final String ORIGIN_TYPE_OTHER = "其他组织";

    /**
     * 是否同意
     */
    public static final String AGREE = "true";
    public static final String NOT_AGREE = "false";

    /**
     * 是否精品：1精品 0普通
     */
    public static final int BOUTIQUE = 1;
    public static final int NO_BOUTIQUE = 0;

    /**
     * 图片后缀
     */
    public static final String PNG_SUFFIX = "png";

    /**
     * 短视频后缀
     */
    public static final String SHORT_VIDEO_SUFFIX = "mp4";

    /**
     * 图集的目录
     */
    public static final String ATLAS_PATH = "atlas";

    /**
     * 短视频目录
     */
    public static final String SHORT_VIDEO_PATH = "shortVideo";

    /**
     * 分类
     */
    public static final String CATEGORY_ALL = "all";
    public static final String CATEGORY_ATLAS = "atlas";
    public static final String CATEGORY_VIDEO = "video";

    /**
     * 等级
     */
    public static final int LEVEL_1 = 1;
    public static final int LEVEL_2 = 2;

}
