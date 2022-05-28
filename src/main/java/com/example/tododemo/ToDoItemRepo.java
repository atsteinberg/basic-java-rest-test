package com.example.tododemo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoItemRepo extends MongoRepository<ToDoItem, String> {
}
