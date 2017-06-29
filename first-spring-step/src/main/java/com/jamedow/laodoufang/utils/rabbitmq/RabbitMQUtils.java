package com.jamedow.laodoufang.utils.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Description
 * <p>
 * Created by Administrator on 2017/4/24.
 */
@Service
public class RabbitMQUtils {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQUtils.class);

    private static String host;
    private static int port;
    private static String username;
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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
