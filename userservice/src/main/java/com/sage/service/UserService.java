
package com.sage.service;

import java.util.List;

import com.sage.binding.User;
import com.sage.entity.UserEntity;

public interface UserService {
	public UserEntity addUser(UserEntity user);

	public UserEntity getUserById(Integer userId);

	public List<User> getAllUser();

	public String deleteUserById(Integer userId);
	
	public UserEntity updateUser(UserEntity user);
	
}
