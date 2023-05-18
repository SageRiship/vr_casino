package com.sage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sage.binding.User;
import com.sage.entity.UserEntity;
import com.sage.service.UserService;

@RestController
public class UserRestController {

	@Autowired
	private UserService userService;

	@PostMapping("/save")
	public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
		UserEntity addUser = userService.addUser(user);
		return new ResponseEntity<>(addUser, HttpStatus.OK);
	}
	
	@GetMapping("/getuser/{userId}")
	public ResponseEntity<UserEntity> getUserById(@PathVariable Integer userId){
		UserEntity user = userService.getUserById(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<User>> getAllUser(){
		List<User> user = userService.getAllUser();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<String> deleteUserById(@PathVariable Integer userId){
		String msg = userService.deleteUserById(userId);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity user) {
		UserEntity updateUser = userService.updateUser(user);
		return new ResponseEntity<>(updateUser, HttpStatus.OK);
	}
}
