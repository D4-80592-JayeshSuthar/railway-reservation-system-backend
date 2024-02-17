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
public class UserDTO {

	private byte[] image;
	
    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private Gender gender;

    @Pattern(regexp = "^[\\d+-]+$")  // Pattern for phone numbers with optional dashes
    private String mobileNumber;

    @Email
    private String email;

    private String username;

    private String country;

    
    private String state;

    @Pattern(regexp = "^\\d{6}$")  // Pattern for 5-digit zip code
    private String zip;
}
