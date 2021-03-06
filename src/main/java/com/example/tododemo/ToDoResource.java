package com.example.tododemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todo")
public class ToDoResource {

    @Autowired
    private ToDoItemRepo repo;

    @GetMapping
    public ResponseEntity<List<ToDoItem>> getAllToDos() {
        List<ToDoItem> toDos = repo.findAll();
        return new ResponseEntity<>(toDos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ToDoItem>> getOneToDo(@PathVariable String id) {
        Optional<ToDoItem> toDo = repo.findById(id);
        return new ResponseEntity<>(toDo, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ToDoItem> createToDo(@RequestBody ToDoItemInput newToDo) {
        ToDoItem toDoToSave = new ToDoItem(newToDo);
        ToDoItem persistedToDo = repo.save(toDoToSave);
        return new ResponseEntity<>(persistedToDo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDoItem> updateToDo(@RequestBody ToDoItemInput update, @PathVariable String id) {
        Optional<ToDoItem> existingToDo = repo.findById(id);
        if (existingToDo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ToDoItem updatedToDo = existingToDo.get().update(update);
        ToDoItem persistedToDo = repo.save(updatedToDo);
        return new ResponseEntity<>(persistedToDo, HttpStatus.OK);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<ToDoItem> patchToDo(@RequestBody ToDoItemInput update, @PathVariable String id) {
        Optional<ToDoItem> existingToDo = repo.findById(id);
        if (existingToDo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ToDoItem updatedToDo = existingToDo.get().partialUpdate(update);
        ToDoItem persistedToDo = repo.save(updatedToDo);
        return new ResponseEntity<>(persistedToDo, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteToDo(@PathVariable String id) {
        try {
            repo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
