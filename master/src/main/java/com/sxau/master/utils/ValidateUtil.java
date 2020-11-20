package com.sxau.master.utils;


import java.util.regex.Pattern;


public class ValidateUtil {

    //用户名校验，3-10个字符，只能由英文字母和数字组成,首字母不能为数字
    public static final String CHECK_USERNAME = "^[a-zA-Z]{1}[a-zA-Z0-9]{2,9}$";
    //密码校验，6-16个字符，只能由英文字母和数字加下划线组成
    public static final String CHECK_PASSWORD = "^[a-zA-Z0-9_]{6,16}$";
    //手机校验
    private static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    //邮箱校验
    private static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\._]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";


    /**
     * 校验字符串是否符合正则表达式
     *
     * @param reg 正则表达式
     * @param str 被校验字符串
     * @return 校验通过返回true
     */
    public static boolean check(String reg, String str) {
        return Pattern.matches(reg, str);
    }

    /**
     * 检查是否是邮箱
     *
     * @param mail 邮箱
     * @return true:是 false:不是
     * @author bawy
     * @date 2020/8/17 14:29
     */
    public static boolean checkMail(String mail) {
        return Pattern.matches(REGEX_EMAIL, mail);
    }

    /**
     * 检查是否是手机号码
     *
     * @param phone 手机号码
     * @return true:是 false:不是
     * @author bawy
     * @date 2020/8/17 14:38
     */
    public static boolean checkPhone(String phone) {
        return Pattern.matches(REGEX_MOBILE, phone);
    }



}
