package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Queue;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Topic<T> {
    private String name;
    private List<Message<T>> queue;
}
