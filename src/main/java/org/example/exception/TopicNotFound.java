package org.example.exception;

public class TopicNotFound extends RuntimeException {
    public TopicNotFound() {
        super("Topic not found: ");
    }
}
