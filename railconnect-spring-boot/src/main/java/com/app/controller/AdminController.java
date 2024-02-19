package com.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.AdminEntity;
import com.app.service.AdminServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminServiceImpl adminService;
	@PostMapping("/signin")
	public ResponseEntity<?> adminSignIn(@RequestBody AdminEntity admin){
		
		Optional<AdminEntity> optAdmin = adminService.getAdmin(admin.getUserName(), admin.getPassword());
		if(optAdmin.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body("success");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failure");
	}
}
