/**
 * 
 */
package com.sl.todo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author user
 *
 */

@Data
@Entity
@Table(name = "TODO")
public class Todo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="TODO_NAME", length=50, nullable=false, unique=false)
	private String name;
	
	@Column(name="TODO_DESCRIPTION", length=500)
	private String description;
	
	@Column(name="DUE_DATE")
	private Date dueDate;
	
	@Column(name="STATUS", length=50, nullable=false)
	private String status;

}
