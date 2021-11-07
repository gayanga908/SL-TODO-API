/**
 * 
 */
package com.sl.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
@RequestMapping("/api/todos")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@PostMapping()
	public ResponseEntity<TodoDTO> saveTodo(@RequestBody TodoDTO payload) {
		return new ResponseEntity<TodoDTO>(todoService.saveTodo(payload), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TodoDTO> getTodo(@PathVariable Long id) {
		return new ResponseEntity<TodoDTO>(todoService.getTodo(id), HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<List<TodoDTO>> getAllTodos() {
		return new ResponseEntity<List<TodoDTO>>(todoService.getAllTodos(), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TodoDTO> updateTodo(@RequestBody TodoDTO payload, @PathVariable Long id) {
		return new ResponseEntity<TodoDTO>(todoService.updateTodo(payload, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<TodoDTO> deleteTodo(@PathVariable Long id) {
		return new ResponseEntity<TodoDTO>(todoService.deleteTodo(id), HttpStatus.NO_CONTENT);
	}

}
