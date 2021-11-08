package com.sl.todo.service;

import java.util.List;

import com.sl.todo.model.TodoDTO;

public interface TodoService {
	
	/**
	 * Saves Todo
	 * @param TodoDTO
	 * @return TodoDTO
	 */
	public TodoDTO saveTodo(TodoDTO todo);
	
	/**
	 * Gets a todo by id
	 * @param id
	 * @return TodoDTO
	 */
	public TodoDTO getTodo(Long id);
	
	/**
	 * Gets all todos
	 * @return List of TodoDTOs
	 */
	public List<TodoDTO> getAllTodos();
	
	/**
	 * Update the Todo of the given id 
	 * @param TodoDTO
	 * @param id
	 * @return TodoDTO
	 */
	public TodoDTO updateTodo(TodoDTO dto, Long id);
	
	/**
	 * Delete a Todo
	 * @param id
	 */
	public void deleteTodo(Long id);

}
