package com.app.dto;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import com.app.entities.Gender;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileDTO {
		private byte[] image;
	
	    @NotBlank
	    private String firstName;

	    @NotBlank
	    private String lastName;

	    @Past
	    private LocalDate birthDate;


	    @NotBlank
	    @Email
	    private String email;

	    @NotBlank
	    @Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number. Must be 10 digits.")
	    private String mobileNumber;

	    @NotBlank
	    private Gender gender; // Use the Gender enum from UserEntity
	    
	    public UserProfileDTO() {
	    	
	    }
}
