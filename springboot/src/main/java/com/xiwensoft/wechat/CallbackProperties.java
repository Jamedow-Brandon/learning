package com.xiwensoft.wechat;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description
 * <p>
 * Created by 365 on 2016/12/26 0026.
 */
@Configuration
@ConfigurationProperties(prefix = "wechat", locations = "classpath:wechat.yml")
public class CallbackProperties {

    private String token;

    private String encodingAESKey;

    private String corpID;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEncodingAESKey() {
        return encodingAESKey;
    }

    public void setEncodingAESKey(String encodingAESKey) {
        this.encodingAESKey = encodingAESKey;
    }

    public String getCorpID() {
        return corpID;
    }

    public void setCorpID(String corpID) {
        this.corpID = corpID;
    }
}
