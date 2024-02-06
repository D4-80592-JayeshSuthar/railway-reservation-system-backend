package com.app.service;

import javax.validation.Valid;

import com.app.entities.UserEntity;

public interface UserService {

	UserEntity userRegistration(@Valid UserEntity user);
//sign up
	
	
	
}
