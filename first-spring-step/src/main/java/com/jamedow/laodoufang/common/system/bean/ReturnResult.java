package com.jamedow.laodoufang.common.system.bean;

/**
 * return code and message,you can create your code and message here.
 */
public enum ReturnResult {
    /**
     * 操作成功
     */
    SUCCESS("10000", "操作成功！");
    /**
     * resultCode小于-10000为异常编码，大于10000为正常编码
     */
    private final String resultCode;
    /**
     * message
     */
    private final String resultMessage;

    /**
     * 构造函数
     *
     * @param resultCode
     * @param resultMessage
     */
    ReturnResult(String resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }
}
