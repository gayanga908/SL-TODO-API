package com.sl.todo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sl.todo.exception.TodoNotFoundException;
import com.sl.todo.exception.ValidationFailedException;
import com.sl.todo.model.TodoDTO;
import com.sl.todo.service.TodoService;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private TodoService todoService;
	
	@Test
    public void testSaveTodoWithOk() throws Exception {
		TodoDTO dto = new TodoDTO();
		dto.setName("Test Name");
		dto.setDescription("Test Description");
		dto.setStatus("Pending");

		when(todoService.saveTodo(dto)).thenReturn(dto);
		
        this.mockMvc.perform(post("/api/todos")
        		.content(asJsonString(dto))
        		.contentType(MediaType.APPLICATION_JSON)
        	    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(content().json(asJsonString(dto)));
    }
	
	@Test
    public void testSaveTodoWithBadRequest() throws Exception {
		TodoDTO dto = new TodoDTO();
		dto.setId(6L);
		dto.setDescription("Test Description");
		dto.setStatus("Pending");

		when(todoService.saveTodo(dto)).thenThrow(ValidationFailedException.class);
		
        this.mockMvc.perform(post("/api/todos")
        		.content(asJsonString(dto))
        		.contentType(MediaType.APPLICATION_JSON)
        	    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isBadRequest());
    }
	
	
	@Test
	public void testGetAllTodosWithOk() throws Exception {
		List<TodoDTO> dtoList = new ArrayList<TodoDTO>();
		TodoDTO dto = new TodoDTO();
		dto.setId(6L);
		dto.setName("Test Name");
		dto.setDescription("Test Description");
		dto.setStatus("Pending");
		
		dtoList.add(dto);
		
		when(todoService.getAllTodos()).thenReturn(dtoList);
		
		this.mockMvc.perform(get("/api/todos"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().json(asJsonString(dtoList)));
	}
	
	@Test
    public void testGetTodoWithOk() throws Exception {
		TodoDTO dto = new TodoDTO();
		dto.setId(6L);
		dto.setName("Test Name");
		dto.setDescription("Test Description");
		dto.setStatus("Pending");
		
		when(todoService.getTodo(6L)).thenReturn(dto);
		
        this.mockMvc.perform(get("/api/todos/6"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(asJsonString(dto)));
    }
	
	@Test
    public void testGetTodoWithNotFound() throws Exception {
		when(todoService.getTodo(6L)).thenThrow(TodoNotFoundException.class);
		
        this.mockMvc.perform(get("/api/todos/6"))
                    .andDo(print())
                    .andExpect(status().isNotFound());
    }
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
