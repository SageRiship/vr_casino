package com.sage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import com.sage.binding.User;
import com.sage.entity.UserEntity;
import com.sage.repo.UserRepo;
import com.sage.service.UserService;
import com.sage.service.UserServiceImpl;

@SpringBootTest(classes = { ServiceMockitoTests.class })
@TestMethodOrder(OrderAnnotation.class)
public class ServiceMockitoTests {

	@Mock
	UserRepo userRepo;

	@InjectMocks
	UserServiceImpl userService;

	@Test
	@Order(1)
	public void test_getAllUser() {
		List<UserEntity> list = new ArrayList<>();
		list.add(new UserEntity(1, "rushi@gmail", "Rushi", "Paymode", "rp", "secret"));
		list.add(new UserEntity(2, "sage@gmail", "Sage", "Paymode", "sp", "secret"));
		userService.getAllUser();
		when(userRepo.findAll()).thenReturn(list);
		assertEquals(2, userService.getAllUser().size());
	}

	@Test
	@Order(2)
	public void test_getUserById() {
		Optional<UserEntity> entity = Optional.of(new UserEntity(1, "rushi@gmail", "Rushi", "Paymode", "rp", "secret"));
		Integer id = 1;
		// userService.getUserById(id);
		when(userRepo.findById(id)).thenReturn(entity); // Mocking
		userService.getUserById(id);
		assertEquals(id, userService.getUserById(id).getUserId());
	}

	@Test
	@Order(3)
	public void test_addUser() {
		UserEntity entity = new UserEntity(1, "rushi@gmail", "Rushi", "Paymode", "rp", "secret");
		when(userRepo.save(entity)).thenReturn(entity); // Mocking
		userService.addUser(entity);
		assertEquals(entity, userService.addUser(entity));
	}

	@Test
	@Order(4)
	public void test_updateUser() {
		UserEntity entity = new UserEntity(1, "rushi@gmal", "Rusi", "Payode", "r", "seret");
		Optional<UserEntity> entity2 = Optional.of(new UserEntity(1, "rushi@gmal", "Rusi", "Payode", "r", "seret"));
		Integer id = 1;
		when(userRepo.findById(id)).thenReturn(entity2);
		when(userRepo.save(entity)).thenReturn(entity); // Mocking
		userService.updateUser(entity);
		assertEquals(entity, userService.updateUser(entity));
	}

	@Test
	@Order(5)
	public void test_deleteUserById() {
		UserEntity entity = new UserEntity(1, "rushi@gmal", "Rusi", "Payode", "r", "seret");

		Integer id = 1;
		userService.deleteUserById(id);
		verify(userRepo, times(1)).deleteById(id);
	}

}
