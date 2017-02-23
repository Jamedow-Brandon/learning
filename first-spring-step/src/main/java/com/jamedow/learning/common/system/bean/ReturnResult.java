package com.jamedow.learning.common.system.bean;

/**
 * return code and message,you can create your code and message here.
 */
public enum ReturnResult {
    /**
     * 操作成功
     */
    SUCCESS("10000", "操作成功！"), /**
     * 新增成功
     */
    CREATE_SUCESS("10001", "新增成功，请查看详情！"), /**
     * 编辑成功
     */
    EDIT_SUCESS("10002", "编辑成功，请查看详情！"), /**
     * 删除成功
     */
    DELETE_SUCESS("10003", "删除成功！"),

    // /////////////////////////////////////////////////////////////////////////
    // 以上为正常编码，以下为异常编码
    // /////////////////////////////////////////////////////////////////////////

    /**
     * 系统异常
     */
    FAIL("-10000", "系统异常，请联系系统管理员，给您带来的不便请见谅，谢谢！"), /**
     * 未发现异常
     */
    NOT_FOUND("-10001", "系统内部异常！"), /**
     * 参数错误异常
     */
    ILLGEAL_PARAMTER("-10002", "参数异常！"), /**
     * 关联内容为空异常
     */
    CONTENT_EMPTY("-10003", "关联内容为空异常！"),

    CHILD_NOT_EMPTY("-10011", "请先删除子部门或清除所有员工后再进行删除！");

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
