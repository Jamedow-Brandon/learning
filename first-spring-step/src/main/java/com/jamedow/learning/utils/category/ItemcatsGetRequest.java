package com.jamedow.learning.utils.category;

import com.taobao.api.ApiRuleException;
import com.taobao.api.BaseTaobaoRequest;
import com.taobao.api.TaobaoRequest;
import com.taobao.api.internal.util.TaobaoHashMap;

import java.util.Date;
import java.util.Map;

/**
 * Created by 365 on 2016/12/19 0019.
 */
public class ItemcatsGetRequest extends BaseTaobaoRequest implements TaobaoRequest {

    private static final String API_METHOD_NAME = "taobao.itemcats.get";

    private String cids;

    private String fields;

    private long parentCid;

    private Date datetime;

    public String getCids() {
        return cids;
    }

    public void setCids(String cids) {
        this.cids = cids;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public long getParentCid() {
        return parentCid;
    }

    public void setParentCid(long parentCid) {
        this.parentCid = parentCid;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @Override
    public String getApiMethodName() {
        return API_METHOD_NAME;
    }

    @Override
    public Map<String, String> getTextParams() {
        TaobaoHashMap txtParams = new TaobaoHashMap();
        txtParams.put("cids", this.cids);
        txtParams.put("fields", this.fields);
        txtParams.put("parent_cid", this.parentCid);
        txtParams.put("datetime", this.datetime);
        if (this.udfParams != null) {
            txtParams.putAll(this.udfParams);
        }

        return txtParams;
    }

    @Override
    public Class getResponseClass() {
        return ItemcatsGetResponse.class;
    }

    @Override
    public void check() throws ApiRuleException {

    }
}
