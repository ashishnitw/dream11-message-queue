package org.example.exception;

public class TopicAlreadyExist extends RuntimeException {
    public TopicAlreadyExist() {
        super("Topic not found: ");
    }
}
