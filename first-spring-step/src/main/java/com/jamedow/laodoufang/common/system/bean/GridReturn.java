package com.jamedow.laodoufang.common.system.bean;

import java.util.List;
import java.util.Map;

/**
 * User: xuning
 * Date: 12-5-31
 * Time: 下午6:06
 * Description:Grid返回对象
 */
public class GridReturn<T> {

    private String code;

    private String msg;

    /**
     * 总共条数
     */
    private long totalCount;

    /**
     * 所有数据
     */
    private List<T> rows;

    private Map params;

    public GridReturn() {
    }

    public GridReturn(List<T> rows) {
        super();
        this.rows = rows;
    }

    public GridReturn(int Total, List<T> rows) {
        this.totalCount = Total;
        this.rows = rows;
    }

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long Total) {
        this.totalCount = Total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public void setReturnResult(ReturnResult returnResult) {
        this.code = returnResult.getResultCode();
        this.msg = returnResult.getResultMessage();
    }
}
