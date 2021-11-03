package com.sl.todo.model;

import java.util.Date;

import lombok.Data;

@Data
public class TodoDTO {
	
	private Long id;
	private String name;
	private String description;
	private Date dueDate;
	private String status;

}
