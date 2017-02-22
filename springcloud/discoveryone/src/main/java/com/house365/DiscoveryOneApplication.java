package com.house365;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DiscoveryOneApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(DiscoveryOneApplication.class).web(true).run(args);
    }

}
