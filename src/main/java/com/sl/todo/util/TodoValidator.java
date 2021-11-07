package com.sl.todo.util;

import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;

import com.sl.todo.exception.ValidationFailedException;
import com.sl.todo.model.TodoDTO;

public class TodoValidator {
	
	public static void validateTodoDTO(TodoDTO dto) {
		if (StringUtils.isBlank(dto.getName())) {
			throw new ValidationFailedException("Name cannot be null", LocalDateTime.now());
		}
		
		if (StringUtils.isNotBlank(dto.getDescription()) && dto.getDescription().length() > 500) {
			throw new ValidationFailedException("Description cannot have more than 500 charactors", LocalDateTime.now());
		}
	}

}
