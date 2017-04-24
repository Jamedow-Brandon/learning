package com.jamedow.learning.service;

import com.jamedow.learning.utils.rabbitmq.Producer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Description
 * <p>
 * Created by Administrator on 2017/4/24.
 */
@Service
public class RabbitMQService {
    @Value("${rabbitmq.host}")
    private String host;
    @Value("${rabbitmq.port}")
    private int port;
    @Value("${rabbitmq.username}")
    private String username;
    @Value("${rabbitmq.password}")
    private String password;

    public void sendMessage(String virtualHost, String queue, String message) {
        try {
            Producer producer = new Producer(host, port, virtualHost, username, password, queue);

            producer.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
