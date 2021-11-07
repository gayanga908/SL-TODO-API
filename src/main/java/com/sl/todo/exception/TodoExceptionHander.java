package com.sl.todo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sl.todo.model.ExceptionResponseDTO;

@ControllerAdvice
public class TodoExceptionHander extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ValidationFailedException.class)
    public ResponseEntity<Object> handleValidationExceptions( ValidationFailedException exception, WebRequest webRequest) {
		ExceptionResponseDTO response = new ExceptionResponseDTO();
        response.setDateTime(LocalDateTime.now());
        response.setErrorMessage(exception.getErrorMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundExceptions(TodoNotFoundException exception, WebRequest webRequest) {
		ExceptionResponseDTO response = new ExceptionResponseDTO();
        response.setDateTime(LocalDateTime.now());
        response.setErrorMessage("Todo not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
