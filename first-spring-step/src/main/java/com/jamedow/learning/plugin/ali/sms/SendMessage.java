package com.jamedow.learning.plugin.ali.sms;

import com.alibaba.fastjson.JSONObject;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017/4/9 0009.
 */
public class SendMessage {
    private static Logger logger = LoggerFactory.getLogger(SendMessage.class);

    public static String sendMessage(String restUrl, String appkey, String secret) {
        String result = "";
        try {
            TaobaoClient client = new DefaultTaobaoClient(restUrl, appkey, secret);
            AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
            req.setExtend("123456");
            req.setSmsType("normal");
            req.setSmsFreeSignName("梧桐落");
            JSONObject smsParams = new JSONObject();
            smsParams.put("number", "3726");
            req.setSmsParamString(smsParams.toJSONString());
            req.setRecNum("15358183725");
            req.setSmsTemplateCode("SMS_60970057");
            AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
            System.out.println(rsp.getBody());
            result = rsp.getBody();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }
}
