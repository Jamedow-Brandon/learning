package com.jamedow.learning.utils.tbk;

import com.taobao.api.ApiRuleException;
import com.taobao.api.BaseTaobaoRequest;
import com.taobao.api.TaobaoRequest;
import com.taobao.api.internal.util.TaobaoHashMap;

import java.util.Map;

/**
 * Created by 365 on 2016/12/19 0019.
 */
public class TbkItemDetailGetRequest extends BaseTaobaoRequest implements TaobaoRequest {

    private static final String API_METHOD_NAME = "taobao.tbk.item.detail.get";

    private String fields;

    private String numIids;

    private long platform;

    private long adzoneId;

    private String unid;


    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getNumIids() {
        return numIids;
    }

    public void setNumIids(String numIids) {
        this.numIids = numIids;
    }

    public long getPlatform() {
        return platform;
    }

    public void setPlatform(long platform) {
        this.platform = platform;
    }

    public long getAdzoneId() {
        return adzoneId;
    }

    public void setAdzoneId(long adzoneId) {
        this.adzoneId = adzoneId;
    }

    public String getUnid() {
        return unid;
    }

    public void setUnid(String unid) {
        this.unid = unid;
    }

    @Override
    public String getApiMethodName() {
        return API_METHOD_NAME;
    }

    @Override
    public Map<String, String> getTextParams() {
        TaobaoHashMap txtParams = new TaobaoHashMap();
        txtParams.put("fields", this.fields);
        txtParams.put("num_iids", this.numIids);
        txtParams.put("platform", this.platform);
        txtParams.put("adzone_id", this.adzoneId);
        txtParams.put("unid", this.unid);
        if (this.udfParams != null) {
            txtParams.putAll(this.udfParams);
        }

        return txtParams;
    }

    @Override
    public Class getResponseClass() {
        return TbkItemDetailGetResponse.class;
    }

    @Override
    public void check() throws ApiRuleException {

    }
}
