
package com.sxau.master.common;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private static final long serialVersionUID = -5785378220885344119L;
    /**
     * 是否成功
     */
    private boolean success = true;
    /**
     * 错误代码
     */
    private String errorCode = "0000";
    private String errorMsg = "成功";
    /**
     * 业务对象
     */
    private T result;

    public Result() {
    }

    public Result(T result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}

