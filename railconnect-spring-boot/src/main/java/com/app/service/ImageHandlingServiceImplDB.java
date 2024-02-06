package com.app.service;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.custom_exceptions.ResourceNotFoundException;

import com.app.dao.UserEntityDao;
import com.app.dto.ApiResponse;

import com.app.entities.UserEntity;

@Service("image_db")
@Transactional
public class ImageHandlingServiceImplDB implements ImageHandlingService {
	@Autowired
	private UserEntityDao userRepo;

	@Override
	public ApiResponse uploadImage(Long userId, MultipartFile image) 
			throws IOException {
		// get user from user id
		UserEntity user = userRepo.findById(userId).
				orElseThrow(() -> 
				new ResourceNotFoundException("Invalid emp ID!!!!"));
		// user found --> PERSISTENT
		// to store the img directly in DB as a BLOB
		user.setImage(image.getBytes());
		return new ApiResponse("Image file uploaded successfully for emp id " + userId);
	}

	@Override
	public byte[] downloadImage(Long userId) throws IOException {
		// get user by id
		UserEntity user = userRepo.
				findById(userId).orElseThrow(() -> new ResourceNotFoundException("Invalid userId !!!!"));
		// user found --> PERSISTENT
		return user.getImage();
	}

}
