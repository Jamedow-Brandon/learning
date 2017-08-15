package com.jamedow.laodoufang.utils.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Description
 * <p>
 * Created by Administrator on 2017/4/24.
 */
@Component
public class RabbitMQUtils {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQUtils.class);

    @Value("${rabbitmq.host}")
    private static String host;
    @Value("${rabbitmq.port}")
    private static int port;
    @Value("${rabbitmq.username}")
    private static String username;
    @Value("${rabbitmq.password}")
    private static String password;

    public static void sendMessage(String virtualHost, String queue, String message) {
        try {
            Producer producer = new Producer(host, port, virtualHost, username, password, queue);
            producer.sendMessage(message);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static void consumerMessage(String virtualHost, String queue) {
        try {
            QueueConsumer consumer = new QueueConsumer(host, port, virtualHost, username, password, queue);
            Thread consumerThread = new Thread(consumer);
            consumerThread.start();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
