package com.todolist.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.todolist.challenge.model.Todo;
import com.todolist.challenge.service.TodoService;

import javax.validation.Valid;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public ResponseEntity<List<Todo>> findAll() {
        return ResponseEntity.ok(todoService.findAll());
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Todo todo) {
        return ResponseEntity.ok(todoService.save(todo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> findById(@PathVariable Integer id) {
        Optional<Todo> todo = todoService.findById(id);
        if (!todo.isPresent()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(todo.get());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> update(@PathVariable Integer id, @Valid @RequestBody Todo todo) {
        if (!todoService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(todoService.save(todo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        if (!todoService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }

        todoService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
