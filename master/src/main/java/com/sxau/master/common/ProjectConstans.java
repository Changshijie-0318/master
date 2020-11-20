package com.sxau.master.common;

/**
 * @BelongsProject: ssm-master
 * @BelongsPackage: com.sxau.common
 * @CreateTime: 2020-08-31 16:55
 * @Description:项目常量值
 */
public class ProjectConstans {
    public static final String USER_INFO = "user";
    public static final String SESSION_USER_INFO_KEY = "userInfo";
    /**
     * 性别
     * 1.男
     * 2.女
     */
    public static final byte USER_GENDER_MAN = 1;
    public static final byte USER_GENDER_WOMAN = 2;

    /**
     * 用户状态
     * 1.生效
     * 0.失效
     */
    public static final byte USER_STATUS_PASS = 1;
    public static final byte USER_GENDER_NO = 0;

    /**
     * 用户身份
     * 1.普通用户
     * 2.管理员身份
     */
    public static final byte USER_ROLE_USER = 1;
    public static final byte USER_ROLE_ROOT = 0;


    /**
     * 商品状态
     * 1.生效
     * 0.失效
     * 2.已被买
     */
    public static final byte BUSI_STATUS_PAY = 2;
    public static final byte BUSI_STATUS_PASS = 1;
    public static final byte BUSI_STATUS_NO = 0;
    /**
     * 上传文件状态
     * 1.生效
     * 0.失效
     */
    public static final byte FILE_STATE_VALID = 1;
    public static final byte FILE_STATE_UN_VALID = 0;

    /**
     * 购物车状态
     * 1.生效
     * 0.失效
     * 2.已被买
     */
    public static final byte SHOP_STATE_VALID = 1;
    public static final byte SHOP_STATE_UNVALID = 0;
    public static final byte SHOP_STATE_BUY = 2;

    /**
     * 求购信息状态
     * 1.生效
     * 0.失效
     */
    public static final byte WANT_STATE_VALID = 1;
    public static final byte WANT_STATE_UNVALID = 0;

    /**
     * 通用
     */
    public static final byte STATE_UNVALID = 0;
    public static final byte STATE_VALID = 1;
    /**
     * 消息通知
     */
    public static final String DEAL_NOTICE = "deal";
    public static final String UN_DEAL_NOTICE = "unDeal";
}
