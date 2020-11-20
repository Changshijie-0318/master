package com.sxau.master.common;

import java.text.MessageFormat;

/**
 * @BelongsProject: ssm-master
 * @BelongsPackage: com.sxau.common
 * @CreateTime: 2020-08-31 17:47
 * @Description:
 */
public class ErrorException extends RuntimeException {
    /**
     * 错误码
     * */
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String message;


    public ErrorException(ResultEnum resultEnum, Object... args) {
        this.code = resultEnum.getErrorCode();
        String tempMsg = resultEnum.getErrorMsg();
        if (args != null){
            tempMsg = MessageFormat.format(tempMsg, args);
        }
        this.message = tempMsg;
    }

    public ErrorException() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorException{" +
                "message='" + message + '\'' +
                '}';
    }
}
