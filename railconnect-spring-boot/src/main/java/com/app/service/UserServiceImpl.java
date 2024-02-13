package com.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.UserEntityDao;
import com.app.dto.UserDTO;
import com.app.dto.UserDetailDTO;
import com.app.dto.UserProfileDTO;
import com.app.entities.UserEntity;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	    @Autowired
	    private UserEntityDao userDao;

	    @Autowired
	    private ModelMapper modelMapper;
	    
	    @Autowired
	    private PasswordEncoder passwordEncoder; // Use a password encoder for security
	    
		@Override
		public UserEntity userRegistration(@Valid UserEntity user) {
			  user.setPassword(passwordEncoder.encode(user.getPassword())); // Encode password before saving
		      user.setActive(true); // Set isActive to true for new users
		      return userDao.save(user);
		}

		@Override
		public UserDTO update(Long userId, @Valid UserDTO userDTO) {
			    UserEntity userEntity = userDao.findById(userId)
		                .orElseThrow(() -> 
		                new ResourceNotFoundException("User not found with ID: " + userId));

		        // Map DTO fields to entity using ModelMapper
		        modelMapper.map(userDTO, userEntity);

		        UserEntity updatedUser = userDao.save(userEntity);
		        return modelMapper.map(updatedUser, UserDTO.class);
		}

		@Override
		public Optional<UserEntity> findById(Long userId) {
			return userDao.findById(userId);
		}
		
	    @Override
	    public void setInactive(Long userId) {
	        UserEntity userEntity = findById(userId)
	                .orElseThrow(() -> 
	                new ResourceNotFoundException("User not found with ID: " + userId));
	        userEntity.setActive(false); // Set isActive to false
	        userDao.save(userEntity);
	    }
	    
	    @Override
	    public UserDetailDTO getUserDetailsById(Long userId) {
	        UserEntity userEntity = userDao.findById(userId)
	                                              .orElseThrow(() ->
	                                              new ResourceNotFoundException("User not found with ID: " + userId));
	        return modelMapper.map(userEntity, UserDetailDTO.class);
	    }
	    
	    
	

	    // Implement the method to set a user's status to active
		@Override
		public void setActive(Long userId) {
		    UserEntity userEntity = findById(userId)
		            .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
		    userEntity.setActive(true); // Set isActive to true
		    userDao.save(userEntity);
		}


		@Override
		 public UserDetailDTO getUserDetailsByEmail(String email) {
	        UserEntity userEntity = userDao.findByEmail(email)
	                                               .orElseThrow(() -> 
		                                              new ResourceNotFoundException("User not found with Email id : " + email));
	        return modelMapper.map(userEntity, UserDetailDTO.class);
	    }

		@Override
		 public UserDetailDTO getUserDetailsByUsername(String username) {
	        UserEntity userEntity = userDao.findByUsername(username)
	                                               .orElseThrow(() ->
	                                               new ResourceNotFoundException("User not found with username : " + username));
	        return modelMapper.map(userEntity, UserDetailDTO.class);
	    }

		@Override
	    public List<UserDetailDTO> getAllUsers() {
	        List<UserEntity> users = userDao.findAll(); // Adjust depending on your repository implementation
	        return users.stream()
	                .map(user -> modelMapper.map(user, UserDetailDTO.class)) // Mapping in service
	                .collect(Collectors.toList());
	    }

		@Override
		public UserProfileDTO getUserProfileDetails(Long userId) {
	        Optional<UserEntity> userEntity = userDao.findById(userId);
	        
	        if (userEntity.isPresent()) {
	            UserEntity userEntity1 = userEntity.get();
	            System.out.println(userEntity1.toString());
	            return modelMapper.map(userEntity1, UserProfileDTO.class);
	        } else {
	            // Handle case when user with given userId is not found
	            throw new ResourceNotFoundException("User with id " + userId + " not found");
	        }  
		}	    
}

