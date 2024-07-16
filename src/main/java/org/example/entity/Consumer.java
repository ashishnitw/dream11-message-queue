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
            for (Map.Entry<Topic<T>, Integer> e : offsets.entrySet()) {
                List<Message<T>> list = e.getKey().getQueue();
                int offset = e.getValue();
                System.out.println(offset + ", " + list.size());
                if (offset < list.size()) {
                    System.out.println("Message Consumed by : " + id + ", " + list.get(offset) + ", offset = " + offset++);
                    offsets.put(e.getKey(), offset);
                }
            }
        }
    }
}
