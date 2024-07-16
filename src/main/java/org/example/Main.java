package org.example;

import org.example.entity.Consumer;
import org.example.entity.Message;
import org.example.entity.Producer;
import org.example.entity.Topic;
import org.example.service.TopicManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            TopicManager topicManager = new TopicManager();
            Topic<String> topic1 = topicManager.createTopic("topic1");

            Consumer consumer1 = Consumer.builder().id("c1").build();
            Consumer consumer2 = Consumer.builder().id("c2").build();
            topicManager.subscribeConsumer("topic1", consumer1);
            topicManager.subscribeConsumer("topic1", consumer2);

            Producer producer1 = Producer.builder().id("p1").topics(Arrays.asList(topic1)).build();
            topicManager.produce(producer1, new Message<>("Message 1"));
            topicManager.produce(producer1, new Message<>("Message 2"));
            topicManager.produce(producer1, new Message<>("Message 3"));

            topicManager.startConsumer(consumer1);
            //topicManager.startConsumer(consumer2);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}