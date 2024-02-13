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
	
	@NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Past
    private LocalDate birthDate;

    private Gender gender;

    @NotBlank
    @Pattern(regexp = "^[\\d+-]+$")  // Pattern for phone numbers with optional dashes
    private String mobileNumber;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String country;

    @NotBlank
    private String state;

    @NotBlank
    @Pattern(regexp = "^\\d{6}$")  // Pattern for 5-digit zip code
    private String zip;
}
