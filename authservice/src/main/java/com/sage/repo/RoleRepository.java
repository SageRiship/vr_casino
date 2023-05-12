package com.sage.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sage.model.ERole;
import com.sage.model.Role;

public interface RoleRepository extends MongoRepository<Role, String>{
	  Optional<Role> findByName(ERole name);
}
