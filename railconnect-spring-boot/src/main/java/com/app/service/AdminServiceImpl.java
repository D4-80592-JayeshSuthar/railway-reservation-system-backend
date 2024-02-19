package com.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.AdminDAO;
import com.app.entities.AdminEntity;
import com.app.exceptions.ResourceNotFoundException;

@Service
public class AdminServiceImpl {
	
	@Autowired
	private AdminDAO adminDao;
	
	public Optional<AdminEntity>  getAdmin(String userName, String password) {
		return Optional.ofNullable(adminDao.getByUserNameAndPassword(userName, password)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Credentials")));
	}
}
