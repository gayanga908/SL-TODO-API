/**
 * 
 */
package com.sl.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sl.todo.entity.Todo;
import com.sl.todo.model.TodoDTO;
import com.sl.todo.repository.TodoDao;
import com.sl.todo.service.TodoService;

/**
 * @author user
 *
 */


@RestController
@RequestMapping("/api/todos")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@PostMapping()
	public TodoDTO saveTodo(@RequestBody TodoDTO payload) {
		return todoService.saveTodo(payload);
	}
	
	@GetMapping("/{id}")
	public TodoDTO getTodo(@PathVariable Long id) {
		return todoService.getTodo(id);
	}
	
	@GetMapping()
	public List<TodoDTO> getAllTodos() {
		return todoService.getAllTodos();
	}
	
	@PutMapping("/{id}")
	public TodoDTO updateTodo(@RequestBody TodoDTO payload, @PathVariable Long id) {
		return todoService.updateTodo(payload, id);
	}
	
	@DeleteMapping("/{id}")
	public TodoDTO deleteTodo(@PathVariable Long id) {
		return todoService.deleteTodo(id);
	}

}
