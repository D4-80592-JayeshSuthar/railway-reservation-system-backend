package com.app.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.app.dto.UserDTO;
import com.app.dto.UserDetailDTO;
import com.app.entities.UserEntity;

public interface UserService {
	//sign up
	UserEntity userRegistration(@Valid UserEntity user);
	
	UserDTO update(Long userId, @Valid UserDTO userDTO);
	
	Optional<UserEntity> findById(Long userId);

	void setInactive(Long userId);
	

	void setActive(Long userId);


	UserDetailDTO getUserDetailsById(Long userId);

	UserDetailDTO getUserDetailsByEmail(String email);

	UserDetailDTO getUserDetailsByUsername(String username);

	List<UserDetailDTO> getAllUsers();
}
