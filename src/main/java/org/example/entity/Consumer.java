package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Consumer<T> implements Runnable {
    public String id;
    public Map<Topic<T>, Integer> offsets;

    @Override
    public void run() {
        while(true) {
            for (Map.Entry<Topic<T>, Integer> entry : offsets.entrySet()) {
                Topic<T> topic = entry.getKey();
                Integer offset = entry.getValue();
                List<Message<T>> queue = topic.getQueue();
                if (offset < queue.size()) {
                    System.out.println("Consumer " + id + " consumed message: " + queue.get(offset).getContent());
                    entry.setValue(offset + 1);
                }
            }
        }
    }
}
