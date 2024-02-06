package com.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.app.entities.UserEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

// haven't used it yet
public class Signup {
		@JsonProperty(access = Access.READ_ONLY)
	    private Long id;
		@NotBlank(message = "First Name required")
	    private String firstName;

	    private String lastName;
	    @Email(message = "Invalid Email!!!")
	    private String email;
	   
	    public Signup(UserEntity user) { // Constructor to map from User entity
	        this.id = user.getId();
	        this.firstName = user.getFirstName();
	        this.lastName = user.getLastName();
	        this.email = user.getEmail();	        
	    }
	    
}

