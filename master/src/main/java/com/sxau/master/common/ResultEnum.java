package com.sxau.master.common;

/**
 * @BelongsProject: ssm-master
 * @BelongsPackage: com.sxau.common
 * @CreateTime: 2020-09-01 09:22
 * @Description:
 */
public enum ResultEnum {
    SUCCESS("0000","成功"),
    PARAM_ERROR("0001","{0}"),
    DUP_SUBMIT_ERROR("0002","非法提交或重复提交"),
    NOT_LOGIN_ERROR("0003","用户未登录  "),
    SESSION_TIMEOUT_ERROR("0004","会话超时，请重新登录"),
    DATA_ERROR("0005", "数据异常，{0}"),
    MAIL_SEND_ERROR("0006", "邮件发送失败，请稍候重试"),
    NO_PERMISSION_ERROR("0007", "当前登录用户无权限进行此操作"),
    AUTH_CODE_ERROR("0008","验证码不正确"),
    ILLEGAL_OPERATION_ERROR("0110", "非法操作，{0}"),
    ILLEGAL_DATA_ERROR("1110", "非法数据，存在非法加密的数据"),
    SYSTEM_ERROR("1111", "系统内部异常，请联系维护人员"),

    /**
     * 系统模块ES0001开始
     */
    SYSTEM_COMMON_ERROR("ES0001","{0}"),
    USERNAME_OR_PASSWORD_ERROR("ES0002","用户名或密码错误"),
    EMAIL_IS_USE("ES0003", "邮箱已经注册"),
    USERNAME_IS_USE("ES0004", "用户名已经被使用"),
    SNO_IS_USE("ES0009", "学号已经被使用"),
    PHONE_IS_USE("ES0010", "手机号已经被使用"),
    USER_STATUS_ABNORMAL("ES0005", "用户状态异常,{0}"),
    OLD_PASSWORD_ERROR("ES0006", "原密码输入错误"),
    SELECT_KEY_ERROR("ES0007", "下拉列表数据对应Key错误，无法获取对应下拉列表数据");

    private String errorCode;
    private String errorMsg;

    ResultEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
