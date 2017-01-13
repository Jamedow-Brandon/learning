package com.xiwensoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Date: 2016/11/29</p>
 *
 * @author XN
 * @version 1.0
 */
@SpringBootApplication
public class WechatApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatApplication.class, args);

    }

}
