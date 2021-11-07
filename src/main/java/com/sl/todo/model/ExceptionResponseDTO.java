package com.sl.todo.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class ExceptionResponseDTO {

	@Getter
	@Setter
	private String errorMessage;
	
	@Getter
	@Setter
	private LocalDateTime dateTime;
}
