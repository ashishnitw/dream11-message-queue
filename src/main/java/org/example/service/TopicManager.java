package org.example.service;

import org.example.entity.Consumer;
import org.example.entity.Message;
import org.example.entity.Producer;
import org.example.entity.Topic;
import org.example.exception.TopicAlreadyExist;
import org.example.exception.TopicNotFound;

import java.util.*;

public class TopicManager<T> {

    Map<String, Topic<T>> topics = new HashMap<>();

    public Topic<T> createTopic(String name) {
        Topic<T> topic = topics.get(name);
        if (topic != null)
            throw new TopicAlreadyExist();
        topic = new Topic<>(name, new LinkedList<>());
        topics.put(name, topic);
        return topic;
    }

    public void subscribeConsumer(String topicName, Consumer consumer) {
        Topic<T> topic = topics.get(topicName);
        if (topic == null)
            throw new TopicNotFound();
        if (consumer.getOffsets() == null)
            consumer.setOffsets(new HashMap<>());
        consumer.getOffsets().put(topic, 0);
        System.out.println("Consumer " + consumer.id + " subscribed to topic " + topicName);
    }

    public void produce(Producer producer, Message message) {
        List<Topic<String>> topicList = producer.getTopics();
        for (Topic t : topicList) {
            t.getQueue().add(message);
            System.out.println("Message produced to topic " + t.getName());
        }
    }

    public void startConsumer(Consumer consumer) {
        Thread t1 = new Thread(consumer);
        t1.start();
        System.out.println("Consumer started..");
    }
}
