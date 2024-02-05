package com.app.service;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.UserEntityDao;
import com.app.dto.Signup;
import com.app.entities.UserEntity;

@Service
@Transactional

public class UserServiceImpl implements UserService {
	    @Autowired
	    private UserEntityDao userDao;

	    @Autowired
	    private PasswordEncoder passwordEncoder; // Use a password encoder for security
	    
		@Override
		public UserEntity userRegistration(@Valid UserEntity user) {
			  user.setPassword(passwordEncoder.encode(user.getPassword())); // Encode password before saving
		      user.setActive(true); // Set isActive to true for new users
		      return userDao.save(user);
		}
	    
	    
	}

