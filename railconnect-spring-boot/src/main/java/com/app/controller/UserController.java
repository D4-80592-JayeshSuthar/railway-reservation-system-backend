package com.app.controller;

import static org.springframework.http.MediaType.IMAGE_GIF_VALUE;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.UserDTO;
import com.app.dto.UserDetailDTO;
import com.app.entities.UserEntity;
import com.app.service.ImageHandlingService;
import com.app.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	@Qualifier("image_db")
	private ImageHandlingService imgService;
	
	public UserController() {
		System.out.println("in ctor of " + getClass());
	}
	
	//to upload the image to the database
	@PostMapping(value = "/images/{userID}", 
			consumes = "multipart/form-data") //consumes : required ONLY for swagger testing
	public ResponseEntity<?> uploadImage(@PathVariable Long userID, 
			@RequestParam MultipartFile imageFile)
			throws IOException {
		System.out.println("in upload img " + userID);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(imgService.uploadImage(userID, imageFile));
	}
	
	//to download the image
	@GetMapping(value = "/images/{userID}", 
			produces = { IMAGE_GIF_VALUE, IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE })
	public ResponseEntity<?> serveUserImage(@PathVariable Long userID) throws IOException {
		System.out.println("in download img " + userID);
		return ResponseEntity.ok(imgService.downloadImage(userID));
	}
	
	// to update the user profile using UserDTO
	@PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateProfile(@PathVariable Long userId, @Valid @RequestBody UserDTO userDTO) {
        UserDTO updatedUserDTO = userService.update(userId, userDTO);
        return ResponseEntity.ok(updatedUserDTO);
    }
	
	// to delete the account (setting the status inactive)
	@PutMapping("/{userId}/status/inactive")
    public ResponseEntity<Void> setInactive(@PathVariable Long userId) {
        userService.setInactive(userId);
        return ResponseEntity.noContent().build();
    }
	
	// to get the user details from the UserProfileDTO
	// confusion 
	

	//	find the user detail (DTO) by its id
	@GetMapping("/by-userid/{userId}")
    public ResponseEntity<UserDetailDTO> getUserDetails(@PathVariable("userId") Long userId) {
        UserDetailDTO userDetailDTO = userService.getUserDetailsById(userId);
        return ResponseEntity.ok(userDetailDTO);
    }
	
	// find the user detail (DTO) by its email
	@GetMapping("/by-email/{email}")
    public ResponseEntity<UserDetailDTO> getUserDetailsByEmail(@PathVariable("email") String email) {
        UserDetailDTO userDetailDTO = userService.getUserDetailsByEmail(email);
        return ResponseEntity.ok(userDetailDTO);
    }
	
	// find the user detail (DTO) by its username
	@GetMapping("/by-username/{username}")
    public ResponseEntity<UserDetailDTO> getUserDetailsByUsername(@PathVariable("username") String username) {
        UserDetailDTO userDetailDTO = userService.getUserDetailsByUsername(username);
        return ResponseEntity.ok(userDetailDTO);
    }
	// get the list of all the users (UserDetailDTO)
	@GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDetailDTO>> getAllUsers() {
        List<UserDetailDTO> userDetailDTOs = userService.getAllUsers();
        return ResponseEntity.ok(userDetailDTOs);
    }
}
