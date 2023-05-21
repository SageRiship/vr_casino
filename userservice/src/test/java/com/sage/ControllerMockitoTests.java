package com.sage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sage.binding.User;
import com.sage.controller.UserRestController;
import com.sage.entity.UserEntity;
import com.sage.service.UserServiceImpl;

@SpringBootTest(classes = { ControllerMockitoTests.class })
@TestMethodOrder(OrderAnnotation.class)
public class ControllerMockitoTests {

	@Mock
	UserServiceImpl userService;

	@InjectMocks
	UserRestController userController;

	List<User> users;
	List<UserEntity> entities;
	UserEntity user;

	@Test
	@Order(1)
	public void test_getAllUser() {
		users = new ArrayList<>();
		users.add(new User("rushi@gmail", "Rushi", "Paymode", "rp"));
		users.add(new User("sage@gmail", "Sage", "Paymode", "sp"));

		when(userService.getAllUser()).thenReturn(users);
		ResponseEntity<List<User>> allUser = userController.getAllUser();
		assertEquals(HttpStatus.OK, allUser.getStatusCode());
		assertEquals(2, allUser.getBody().size());
	}

	@Test
	@Order(2)
	public void test_getUserById() {
		user = new UserEntity(1, "rushi@gmail", "Rushi", "Paymode", "rp", "secret");
		Integer id = 1;
		when(userService.getUserById(id)).thenReturn(user);
		ResponseEntity<UserEntity> userById = userController.getUserById(id);
		assertEquals(HttpStatus.OK, userById.getStatusCode());
	}

	@Test
	@Order(3)
	public void test_createUser() {
		user = new UserEntity(1, "rushi@gmail", "Rushi", "Paymode", "rp", "secret");
		when(userService.addUser(user)).thenReturn(user);
		ResponseEntity<UserEntity> createUser = userController.createUser(user);
		assertEquals(HttpStatus.OK, createUser.getStatusCode());
	}
	@Test
	@Order(4)
	public void test_updateUser() {
		user = new UserEntity(1, "rushi@gmail", "Rushi", "Paymode", "rp", "secret");
		when(userService.updateUser(user)).thenReturn(user);
		ResponseEntity<UserEntity> updateUser = userController.updateUser(user);
		assertEquals(HttpStatus.OK, updateUser.getStatusCode());
	}

	@Test
	@Order(5)
	public void test_deleteUser() {
		user = new UserEntity(1, "rushi@gmail", "Rushi", "Paymode", "rp", "secret");
		when(userService.deleteUserById(1)).thenReturn("User Deleted");
		ResponseEntity<String> deleteUserById = userController.deleteUserById(1);
		assertEquals(HttpStatus.OK, deleteUserById.getStatusCode());
		assertEquals("User Deleted", deleteUserById.getBody());
	}
	
}
