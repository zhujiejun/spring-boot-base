package com.zhujiejun.java.tube;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.tubemq.client.config.TubeClientConfig;
import org.apache.tubemq.client.factory.MessageSessionFactory;
import org.apache.tubemq.client.factory.TubeSingleSessionFactory;
import org.apache.tubemq.client.producer.MessageProducer;
import org.apache.tubemq.client.producer.MessageSentResult;
import org.apache.tubemq.corebase.Message;

public class SyncProducerExample {
    public static void main(String[] args) throws Throwable {
        final String masterHostAndPort = "node101:18000";
        final TubeClientConfig clientConfig = new TubeClientConfig(masterHostAndPort);
        final MessageSessionFactory messageSessionFactory = new TubeSingleSessionFactory(clientConfig);
        final MessageProducer messageProducer = messageSessionFactory.createProducer();
        final String topic = "demo";
        final String body = "This is a test message from single-session-factory!";
        byte[] bodyData = StringUtils.getBytesUtf8(body);
        messageProducer.publish(topic);
        Message message = new Message(topic, bodyData);
        MessageSentResult result = messageProducer.sendMessage(message);
        if (result.isSuccess()) {
            System.out.println("sync send message : " + message);
        }
        messageProducer.shutdown();
        messageSessionFactory.shutdown();
    }
}
