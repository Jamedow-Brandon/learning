package com.jamedow.learning.utils.rabbitmq;

import com.jamedow.learning.utils.webcollector.CompanyCrawler;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/2/21 0021.
 */
public class QueueConsumer extends EndPoint implements Runnable, Consumer {

    public QueueConsumer(String host, int port, String virtualHost, String username, String password, String endPointName) throws IOException, TimeoutException {
        super(host, port, virtualHost, username, password, endPointName);
    }

    public void run() {
        try {
            //start consuming messages. Auto acknowledge messages.
            channel.basicConsume(endPointName, true, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when consumer is registered.
     */
    public void handleConsumeOk(String consumerTag) {
        System.out.println("Consumer " + consumerTag + " registered");
    }

    /**
     * Called when new message is available.
     */
    @Override
    public void handleDelivery(String consumerTag, Envelope env,
                               AMQP.BasicProperties props, byte[] body) throws IOException {
        String href = SerializationUtils.deserialize(body);
        try {
            CompanyCrawler crawler = new CompanyCrawler("crawler", false);
            crawler.addSeed(href);

            crawler.setThreads(1);
            crawler.start(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleCancel(String consumerTag) {
    }

    public void handleCancelOk(String consumerTag) {
    }

    public void handleRecoverOk(String consumerTag) {
        System.out.println("===========================");
    }

    public void handleShutdownSignal(String consumerTag, ShutdownSignalException arg1) {
    }
}
