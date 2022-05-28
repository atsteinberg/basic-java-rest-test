package com.example.tododemo;

import lombok.Getter;
import lombok.Setter;

public class ToDoItemInput {
    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String completed;

    public ToDoItemInput() {
    }

    public ToDoItemInput(String description, String completed) {
        setDescription(description);
        setCompleted(completed);
    }
}
