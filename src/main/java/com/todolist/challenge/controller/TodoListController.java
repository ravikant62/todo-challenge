package com.todolist.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.todolist.challenge.model.TodoList;
import com.todolist.challenge.service.TodoListService;
import com.todolist.challenge.service.TodoService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/lists")
public class TodoListController {

    @Autowired
    private TodoListService todoListService;

    @Autowired
    private TodoService todoService;

    @GetMapping
    public ResponseEntity<List<TodoList>> findAll() {
        return ResponseEntity.ok(todoListService.findAll());
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody TodoList todoList) {
        return ResponseEntity.ok(todoListService.save(todoList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoList> findById(@PathVariable Integer id) {
        Optional<TodoList> todoList = todoListService.findById(id);
        if (!todoList.isPresent()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(todoList.get());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoList> update(@PathVariable Integer id, @Valid @RequestBody TodoList todoList) {
        if (!todoListService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(todoListService.save(todoList));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        if (!todoListService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }

        try {
            todoService.deleteByListId(id);
        } catch (Throwable throwable) {
            System.out.println("Error deleting Todos by list id, perhaps you are running tests?");
        }

        todoListService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
