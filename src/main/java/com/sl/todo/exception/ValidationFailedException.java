package com.sl.todo.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ValidationFailedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private String errorMessage;
	
	@Getter
	@Setter
	private LocalDateTime dateTime;

}
