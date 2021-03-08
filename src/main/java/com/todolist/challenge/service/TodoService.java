package com.todolist.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.challenge.model.Todo;
import com.todolist.challenge.repository.TodoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Optional<Todo> findById(Integer id) {
        return todoRepository.findById(id);
    }

    public Todo save(Todo note) {
    	note.setLastUpdated(LocalDateTime.now());
        return todoRepository.save(note);
    }

    public void deleteById(Integer id) {
        todoRepository.deleteById(id);
    }

    /**
     * deletes all Todos given an ID of their todolist
     *
     * @param id the todolist's id
     */
    public void deleteByListId(Integer id) {
        
        List<Todo> temp;
        temp = findAll();
        for (Todo todo : temp) {
            if (todo.getTodolist().getId().equals(id)) {
                deleteById(todo.getId());
            }
        }
    }
}
