package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Producer {
    String id;
    List<Topic<String>> topics;

    public Producer(String id) {
        this.id = id;
        this.topics = new ArrayList<>();
    }
}
