package com.sage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sage.binding.User;
import com.sage.controller.UserRestController;
import com.sage.entity.UserEntity;
import com.sage.service.UserServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
@ComponentScan(basePackages = "com.sage")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = { ControllerMockMvcTests.class })
//@WebMvcTest(value = UserRestController.class)
public class ControllerMockMvcTests {

	@Autowired
	MockMvc mockMvc;

	@Mock
	UserServiceImpl userService;

	@InjectMocks
	UserRestController userController;

	List<User> users;
	List<UserEntity> entities;
	UserEntity user;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	@Order(1)
	public void test_getAllUser() throws Exception {
		users = new ArrayList<>();
		users.add(new User("rushi@gmail", "Rushi", "Paymode", "rp"));
		users.add(new User("sage@gmail", "Sage", "Paymode", "sp"));

		when(userService.getAllUser()).thenReturn(users);

//		this.mockMvc.perform(get("/getall"))
//		.andExpect(status().isFound())
//		.andExpect(MockMvcResultMatchers.jsonPath(".uName").value("Rushi"))
//		.andDo(print());

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getall");
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);
	}

	@Test
	@Order(2)
	public void test_getUserById() throws Exception {
		user = new UserEntity(1, "rushi@gmail", "Rushi", "Paymode", "rp", "secret");
		Integer userId = 1;
		when(userService.getUserById(userId)).thenReturn(user);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getuser/{userId}", userId);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);
	}

	@Test
	@Order(3)
	public void test_createUser() throws Exception {
		user = new UserEntity(1, "rushi@gmail", "Rushi", "Paymode", "rp", "secret");
		ObjectMapper mapper = new ObjectMapper();
		String jsonUser = mapper.writeValueAsString(user);
		when(userService.addUser(user)).thenReturn(user);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/save").contentType("application/json").content(jsonUser);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);
	}
	
	@Test
	@Order(4)
	public void test_updateUser() throws Exception {
		user = new UserEntity(1, "rushi@gmail", "Rushi", "Paymode", "rp", "secret");
		ObjectMapper mapper = new ObjectMapper();
		String jsonUser = mapper.writeValueAsString(user);
		when(userService.updateUser(user)).thenReturn(user);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/update").contentType("application/json").content(jsonUser);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);
	}

	@Test
	@Order(5)
	public void test_deleteUser() throws Exception {
		user = new UserEntity(1, "rushi@gmail", "Rushi", "Paymode", "rp", "secret");
		Integer userId = 1;
		when(userService.deleteUserById(1)).thenReturn("User Deleted");
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/delete/{userId}",userId);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);
		assertEquals("User Deleted", response.getContentAsString());
	}

}
