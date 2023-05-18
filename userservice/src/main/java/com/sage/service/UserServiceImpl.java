package com.sage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sage.binding.User;
import com.sage.entity.UserEntity;
import com.sage.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserEntity addUser(UserEntity user) {
		UserEntity entity = new UserEntity();
		// BeanUtils.copyProperties(user, entity);
		UserEntity save = userRepo.save(user);
		return save;
	}

	@Override
	public UserEntity getUserById(Integer userId) {
		Optional<UserEntity> findById = userRepo.findById(userId);
		if (findById.isPresent()) {
			UserEntity userEntity = findById.get();
			return userEntity;
		}
		return null;
	}

	@Override
	public List<User> getAllUser() {
		List<UserEntity> findAll = userRepo.findAll();
		List<User> list = new ArrayList<>();

		for (UserEntity entity : findAll) {
			User user = new User();
			BeanUtils.copyProperties(entity, user);
			list.add(user);
		}
		return list;
	}

	@Override
	public String deleteUserById(Integer userId) {
		try {
			userRepo.deleteById(userId);
			return "User deleted succssfully : " + userId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "User deletion failed";
	}

	@Override
	public UserEntity updateUser(UserEntity user) {
		System.out.println("UserEntity : "+user);
		if (userRepo.findById(user.getUserId()).isPresent()) {
			UserEntity userEntity = userRepo.findById(user.getUserId()).get();
			if (user.getDisplayName() != null) {
				userEntity.setDisplayName(user.getDisplayName());
			} else if (user.getFirstName() != null) {
				userEntity.setFirstName(user.getFirstName());
			} else if (user.getLastName() != null) {
				userEntity.setLastName(user.getLastName());
			}
			if (user.getPassword() != null) {
				userEntity.setPassword(user.getPassword());
			}
			if (user.getUname() != null) {
				userEntity.setUname(user.getUname());
			}
			UserEntity save = userRepo.save(userEntity);
			return save;
		}
		return null;
	}

}
