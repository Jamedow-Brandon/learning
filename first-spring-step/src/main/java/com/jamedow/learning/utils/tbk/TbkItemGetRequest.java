package com.jamedow.learning.utils.tbk;

import com.taobao.api.ApiRuleException;
import com.taobao.api.BaseTaobaoRequest;
import com.taobao.api.TaobaoRequest;
import com.taobao.api.internal.util.TaobaoHashMap;

import java.util.Map;

/**
 * Created by 365 on 2016/12/19 0019.
 */
public class TbkItemGetRequest extends BaseTaobaoRequest implements TaobaoRequest {

    private String API_METHOD_NAME = "taobao.tbk.item.get";

    private String fields;

    private String q;

    private String cat;

    private String itemloc;

    private String sort;

    private boolean isTmall;

    private boolean isOverseas;

    private long startPrice;

    private long endPrice;

    private long startTkRate;

    private long endTkRate;

    private long platform;

    private long pageNo;

    private long pageSize;

    public java.lang.String getFields() {
        return fields;
    }

    public void setFields(java.lang.String fields) {
        this.fields = fields;
    }

    public java.lang.String getQ() {
        return q;
    }

    public void setQ(java.lang.String q) {
        this.q = q;
    }

    public java.lang.String getCat() {
        return cat;
    }

    public void setCat(java.lang.String cat) {
        this.cat = cat;
    }

    public String getItemloc() {
        return itemloc;
    }

    public void setItemloc(String itemloc) {
        this.itemloc = itemloc;
    }

    public java.lang.String getSort() {
        return sort;
    }

    public void setSort(java.lang.String sort) {
        this.sort = sort;
    }

    public boolean isTmall() {
        return isTmall;
    }

    public void setTmall(boolean tmall) {
        isTmall = tmall;
    }

    public boolean isOverseas() {
        return isOverseas;
    }

    public void setOverseas(boolean overseas) {
        isOverseas = overseas;
    }

    public long getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(long startPrice) {
        this.startPrice = startPrice;
    }

    public long getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(long endPrice) {
        this.endPrice = endPrice;
    }

    public long getStartTkRate() {
        return startTkRate;
    }

    public void setStartTkRate(long startTkRate) {
        this.startTkRate = startTkRate;
    }

    public long getEndTkRate() {
        return endTkRate;
    }

    public void setEndTkRate(long endTkRate) {
        this.endTkRate = endTkRate;
    }

    public long getPlatform() {
        return platform;
    }

    public void setPlatform(long platform) {
        this.platform = platform;
    }

    public long getPageNo() {
        return pageNo;
    }

    public void setPageNo(long pageNo) {
        this.pageNo = pageNo;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String getApiMethodName() {
        return API_METHOD_NAME;
    }

    @Override
    public Map<String, String> getTextParams() {
        TaobaoHashMap txtParams = new TaobaoHashMap();
        txtParams.put("fields", this.fields);
        txtParams.put("q", this.q);
        txtParams.put("cat", this.cat);
        txtParams.put("itemloc", this.itemloc);
        txtParams.put("sort", this.sort);
        txtParams.put("isTmall", this.isTmall);
        txtParams.put("isOverseas", this.isOverseas);
        txtParams.put("startPrice", this.startPrice);
        txtParams.put("endPrice", this.endPrice);
        txtParams.put("startTkRate", this.startTkRate);
        txtParams.put("endTkRate", this.endTkRate);
        txtParams.put("platform", this.platform);
        txtParams.put("pageNo", this.pageNo);
        txtParams.put("pageSize", this.pageSize);
        if (this.udfParams != null) {
            txtParams.putAll(this.udfParams);
        }

        return txtParams;
    }

    @Override
    public Class getResponseClass() {
        return TbkItemGetResponse.class;
    }

    @Override
    public void check() throws ApiRuleException {

    }
}
