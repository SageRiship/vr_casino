package com.sage.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {

	private Integer userId;
	private String uname;
	private String firstName;
	private String lastName;
	private String displayName;
	private String password;
	
}
