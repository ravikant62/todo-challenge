package com.todolist.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todolist.challenge.model.TodoList;

@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Integer> {
}
