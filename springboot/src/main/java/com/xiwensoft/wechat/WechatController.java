package com.xiwensoft.wechat;

import com.xiwensoft.wechat.aes.WXBizMsgCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Date: 2016/12/13</p>
 *
 * @author XN
 * @version 1.0
 */
@RestController
public class WechatController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CallbackProperties callbackProperties;

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String add() {
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
        return "OK";
    }

    @RequestMapping(value = "/call", method = RequestMethod.POST)
    public String call() {
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
        return "CALL";
    }


    @RequestMapping(value = "checkwechatcallback", method = RequestMethod.POST)
    public String checkWeChatCallback(HttpServletResponse response,
                                      @RequestParam(value = "msg_signature") String sVerifyMsgSig,
                                      @RequestParam(value = "timestamp") String sVerifyTimeStamp,
                                      @RequestParam(value = "nonce") String sVerifyNonce,
                                      @RequestParam(value = "echostr") String sVerifyEchoStr) throws Exception {


        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(callbackProperties.getToken(), callbackProperties.getEncodingAESKey(),
                callbackProperties.getCorpID());

        String sEchoStr = null; //需要返回的明文
        try {
            sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp,
                    sVerifyNonce, sVerifyEchoStr);
            System.out.println("verifyurl echostr: " + sEchoStr);
            // 验证URL成功，将sEchoStr返回
            response.setStatus(200);
        } catch (Exception e) {
            //验证URL失败，错误原因请查看异常
            e.printStackTrace();
        }
        return sEchoStr;
    }
}
