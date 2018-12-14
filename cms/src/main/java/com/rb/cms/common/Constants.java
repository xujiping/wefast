package com.rb.cms.common;

import java.io.File;

/**
 * 全局静态
 *
 * @author xujiping
 * @date 2018/11/9 17:53
 */
public class Constants {

    /**
     * 系统分隔符
     */
    public static final char SYS_SEPARATOR = File.separatorChar;

    /**
     * 获取OSS文件url时，文件名不能以'/'开头
     */
    public static final char OSS_NOT_START = '/';

    /**
     * 系统
     */
    public static final String[] SYSTEM = {"", "后台管理系统"};

    /**
     * 后台管理系统权限类别
     */
    public static final String[] CMS_PERMISSION_TYPE = {"", "目录", "菜单", "按钮"};

    /**
     * 状态
     */
    public static final String[] STATUS = {"禁止", "正常"};

    /**
     * 状态
     */
    public static final String STAT_NORMAL = "normal";
    public static final String STAT_AUDIT = "audit";
    public static final String STAT_PASS = "pass";
    public static final String STAT_FAILED = "failed";
    public static final String STAT_BLOCK = "block";

    /**
     * 收入/支出
     */
    public static final int INCOME = 1;
    public static final int NO_INCOME = 2;

    /**
     * 1金币 2零钱 3福豆
     */
    public static final int TYPE_GOLD = 1;
    public static final int TYPE_COIN = 2;
    public static final int TYPE_BEAN = 3;

    public static final String LISTEN_WEAL_TITLE = "音频宝箱奖励";
    public static final String SLOTS_WEAL_TITLE = "幸运大抽奖";
    public static final String VIDEO_ATLAS_WEAL_TITLE = "视频/图集宝箱奖励";

    /**
     * 提现状态
     */
    public static final int WITHDRAW_NO_FINISH = 0;
    public static final int WITHDRAW_FINISH = 1;
    public static final int WITHDRAW_PASS = 2;
    public static final int WITHDRAW_NO_PASS = 3;


    /**
     * 1元=10000金币
     */
    public static final int CONVERTIBLE_PROPORTION = 10000;

    /**
     * 消息推送类型
     * 通知：message
     * 公告：notice
     */
    public static final String PUSH_TYPE_MESSAGE = "message";
    public static final String PUSH_TYPE_NOTICE = "notice";

    public static final String PUSH_TITLE_WITHDRAW_TITLE = "提现通知";
    public static final String PUSH_TITLE_WITHDRAW_CONTENT = "您申请的提现已经审核通过，现金到账可能稍有延迟，请耐心等待。";
    public static final String PUSH_TITLE_WITHDRAW_NO_PASS_CONTENT = "很抱歉，因您涉嫌违规操作，您申请的提现审核不通过";
    public static final String PUSH_FEEDBACK_TITLE = "问题及反馈通知";
    public static final String PUSH_FEEDBACK_CONTENT = "您提交的问题及建议我们已经收到，我们的攻城狮已经在处理了，谢谢您的反馈";


    /**
     * 支付宝用户类型：公司账户
     */
    public static String ALIPAY_INFO_USER_TYPE_COMPANY = "1";

    /**
     * 安全级别：0正常，1冻结，2警告，3延迟，4异常，5优先
     */
    public static int[] SAFETY_LEVEL = {0, 1, 2, 3, 4, 5};

    /**
     * APP上传后的访问路径
     */
    public static final String FILE_UPLOAD_GET_PATH = "/apk/";

    /**
     * apk文件目录
     */
    public static final String FILE_APK_PATH = "apk/";

    public static final String FIELD_UPLOAD_GET_PATH = "field/drawable-xhdpi/";

    /**
     *  1支付宝 2微信
     */
    public static final int TYPE_ALIPAY = 1;
    public static final int TYPE_WEIXIN = 2;

    /**
     * oss服务器图片宽度
     */
    public static final int OSS_IMG_WIDTH = 100;

}
