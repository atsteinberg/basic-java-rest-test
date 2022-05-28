package com.example.tododemo;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class ToDoItem {
    @Id
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private boolean completed;

    public ToDoItem() {
    }

    public ToDoItem(ToDoItemInput input) {
        setDescription(input.getDescription());
        setCompleted(input.getCompleted() != null && input.getCompleted().equals("true"));
        ObjectId id = new ObjectId();
        setId(String.valueOf(id));
    }

    public ToDoItem update(ToDoItemInput update) {
        setDescription(update.getDescription());
        setCompleted(update.getCompleted() != null && update.getCompleted().equals("true"));
        return this;
    }

    public ToDoItem patch(ToDoItemInput patch) {
        if (patch.getDescription() != null) {
            setDescription(patch.getDescription());
        }
        if (patch.getCompleted() != null) {
            setCompleted(patch.getCompleted() != null && patch.getCompleted().equals("true"));
        }
        return this;
    }
}
