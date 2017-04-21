package com.jamedow.learning.utils.rabbitmq;

import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/2/21 0021.
 */
public class Producer extends EndPoint {

    public Producer(String host, int port, String virtualHost, String username, String password, String endPointName) throws IOException, TimeoutException {
        super(host, port, virtualHost, username, password, endPointName);
    }

    public void sendMessage(Serializable object) throws IOException {
        channel.basicPublish("", endPointName, null, SerializationUtils.serialize(object));
    }
}
