package com.sl.todo.mapper;

import org.springframework.stereotype.Component;

import com.sl.todo.entity.Todo;
import com.sl.todo.model.TodoDTO;

@Component
public class TodoMapper {
	
	public Todo fromDTO(TodoDTO todoDto) {
		Todo todoEntity = new Todo();
		todoEntity.setName(todoDto.getName());
		todoEntity.setDescription(todoDto.getDescription());
		todoEntity.setDueDate(todoDto.getDueDate());
		todoEntity.setStatus(todoDto.getStatus());
		
		return todoEntity;
	}
	
	public TodoDTO toDTO(Todo todoEntity) {
		TodoDTO dto = new TodoDTO();
		dto.setId(todoEntity.getId());
		dto.setName(todoEntity.getName());
		dto.setDescription(todoEntity.getDescription());
		dto.setDueDate(todoEntity.getDueDate());
		dto.setStatus(todoEntity.getStatus());
		return dto;
	}

}
