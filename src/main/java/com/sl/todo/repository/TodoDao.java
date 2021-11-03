package com.sl.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sl.todo.entity.Todo;

@Repository
public interface TodoDao extends JpaRepository<Todo, Long> {

}
