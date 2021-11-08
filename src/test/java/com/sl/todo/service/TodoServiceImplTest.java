package com.sl.todo.service;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.sl.todo.entity.Todo;
import com.sl.todo.exception.TodoNotFoundException;
import com.sl.todo.exception.ValidationFailedException;
import com.sl.todo.mapper.TodoMapper;
import com.sl.todo.model.TodoDTO;
import com.sl.todo.repository.TodoDao;

@SpringBootTest
public class TodoServiceImplTest {

	private Todo entity;

	private Optional<Todo> optionalEntity;

	@Mock
	private TodoMapper todoMapper;

	@Mock
	private TodoDao todoDao;

	@InjectMocks
	private TodoService todoService = new TodoServiceImpl();

	@BeforeEach
	public void setUp() {
		// MockitoAnnotations.initMocks(this);
		entity = new Todo();
		entity.setId(1L);
		entity.setName("Test Name");
		entity.setDescription("Test Description");
		entity.setStatus("Pending");

		optionalEntity = Optional.of(entity);

	}

	@Test
	public void testSaveTodo() {
		TodoDTO dto = new TodoDTO();
		dto.setName("Test Name");
		dto.setDescription("Test Description");
		dto.setStatus("Pending");
		when(todoMapper.fromDTO(dto)).thenReturn(entity);
		when(todoMapper.toDTO(entity)).thenReturn(dto);
		when(todoDao.saveAndFlush(entity)).thenReturn(entity);
		assertNotNull(todoService.saveTodo(dto));
	}

	@Test
	public void testSaveTodoWithNullName() {

		Assertions.assertThrows(ValidationFailedException.class, () -> {
			TodoDTO dto = new TodoDTO();
			dto.setDescription("Test Description");
			dto.setStatus("Pending");
			when(todoMapper.fromDTO(dto)).thenReturn(entity);
			when(todoMapper.toDTO(entity)).thenReturn(dto);
			when(todoDao.saveAndFlush(entity)).thenReturn(entity);
			todoService.saveTodo(dto);
		});

	}

	@Test
	public void testSaveTodoWithInvalidDescriprion() {

		Assertions.assertThrows(ValidationFailedException.class, () -> {
			TodoDTO dto = new TodoDTO();
			dto.setName("Test Name");
			dto.setDescription(
					"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
			dto.setStatus("Pending");
			when(todoMapper.fromDTO(dto)).thenReturn(entity);
			when(todoMapper.toDTO(entity)).thenReturn(dto);
			when(todoDao.saveAndFlush(entity)).thenReturn(entity);
			todoService.saveTodo(dto);
		});

	}

	@Test
	public void testGetTodo() {
		TodoDTO dto = new TodoDTO();
		dto.setName("Test Name");
		dto.setDescription("Test Description");
		dto.setStatus("Pending");
		when(todoDao.findById(6L)).thenReturn(optionalEntity);
		when(todoMapper.toDTO(optionalEntity.get())).thenReturn(dto);
		assertNotNull(todoService.getTodo(6L));

	}

	@Test
	public void testGetTodoWithInvalidId() {
		Assertions.assertThrows(TodoNotFoundException.class, () -> {
			TodoDTO dto = new TodoDTO();
			dto.setName("Test Name");
			dto.setDescription("Test Description");
			dto.setStatus("Pending");
			when(todoDao.findById(6L)).thenReturn(Optional.ofNullable(null));
			when(todoMapper.toDTO(optionalEntity.get())).thenReturn(dto);
			todoService.getTodo(6L);
		});

	}

}
