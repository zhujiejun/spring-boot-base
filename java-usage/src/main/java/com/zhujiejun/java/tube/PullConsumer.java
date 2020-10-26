package com.zhujiejun.java.tube;

import org.apache.tubemq.client.config.ConsumerConfig;
import org.apache.tubemq.client.consumer.ConsumePosition;
import org.apache.tubemq.client.consumer.ConsumerResult;
import org.apache.tubemq.client.consumer.PullMessageConsumer;
import org.apache.tubemq.client.factory.MessageSessionFactory;
import org.apache.tubemq.client.factory.TubeSingleSessionFactory;
import org.apache.tubemq.corebase.Message;
import org.apache.tubemq.corebase.utils.ThreadUtils;

import java.util.List;

public class PullConsumer {
    public static void main(String[] args) throws Throwable {
        final String masterHostAndPort = "node101:18000";
        final String topic = "demo";
        final String group = "test-group";
        final ConsumerConfig consumerConfig = new ConsumerConfig(masterHostAndPort, group);
        consumerConfig.setConsumePosition(ConsumePosition.CONSUMER_FROM_LATEST_OFFSET);
        final MessageSessionFactory messageSessionFactory = new TubeSingleSessionFactory(consumerConfig);
        final PullMessageConsumer messagePullConsumer = messageSessionFactory.createPullConsumer(consumerConfig);
        messagePullConsumer.subscribe(topic, null);
        messagePullConsumer.completeSubscribe();
        // wait for client to join the exact consumer queue that consumer group allocated
        while (!messagePullConsumer.isPartitionsReady(1000)) {
            ThreadUtils.sleep(1000);
        }
        while (true) {
            ConsumerResult result = messagePullConsumer.getMessage();
            if (result.isSuccess()) {
                List<Message> messageList = result.getMessageList();
                for (Message message : messageList) {
                    System.out.println("received message : " + message);
                }
                messagePullConsumer.confirmConsume(result.getConfirmContext(), true);
            } else {
                if (result.getErrCode() == 400) {
                    ThreadUtils.sleep(100);
                } else {
                    if (result.getErrCode() != 404) {
                        System.out.printf("Receive messages errorCode is %d, Error message is %s%n", result.getErrCode(), result.getErrMsg());
                    }
                }
            }
        }
    }
}
