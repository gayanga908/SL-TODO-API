package com.sl.todo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sl.todo.entity.Todo;
import com.sl.todo.mapper.TodoMapper;
import com.sl.todo.model.TodoDTO;
import com.sl.todo.repository.TodoDao;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoMapper todoMapper;
	
	@Autowired
	private TodoDao todoDao;
	
	@Override
	public TodoDTO saveTodo(TodoDTO todo) {
		todo.setStatus("pending");
		Todo todoEntitiy = todoDao.saveAndFlush(todoMapper.fromDTO(todo));
		return todoMapper.toDTO(todoEntitiy);
	}

	@Override
	public TodoDTO getTodo(Long id) {
		Optional<Todo> todoEntitiy = todoDao.findById(id);
		
		if (todoEntitiy.isPresent()) {
			return todoMapper.toDTO(todoEntitiy.get());
		}
		
		return null;
	}

	@Override
	public List<TodoDTO> getAllTodos() {
		List<Todo> todoEntityList = todoDao.findAll();
		List<TodoDTO> dtoList = new ArrayList<TodoDTO>();
		todoEntityList.stream()
		.forEach(todoEntity -> {
			dtoList.add(todoMapper.toDTO(todoEntity));
			
		});
		return dtoList;
	}

	@Override
	public TodoDTO updateTodo(TodoDTO dto, Long id) {
		Optional<Todo> todoEntitiy = todoDao.findById(id);
		
		if (todoEntitiy.isPresent()) {
			Todo entity = todoEntitiy.get();
			entity.setName(dto.getName());
			entity.setDescription(dto.getDescription());
			entity.setDueDate(dto.getDueDate());
			entity.setStatus(dto.getStatus());
			return todoMapper.toDTO(todoDao.saveAndFlush(entity));
		}
		return null;
	}

	@Override
	public TodoDTO deleteTodo(Long id) {
		todoDao.deleteById(id);
		return null;
	}
	
	
	
	

}
