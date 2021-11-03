package com.sl.todo.service;

import java.util.List;

import com.sl.todo.model.TodoDTO;

public interface TodoService {
	
	public TodoDTO saveTodo(TodoDTO todo);
	
	public TodoDTO getTodo(Long id);
	
	public List<TodoDTO> getAllTodos();
	
	public TodoDTO updateTodo(TodoDTO dto, Long id);
	
	public TodoDTO deleteTodo(Long id);

}
