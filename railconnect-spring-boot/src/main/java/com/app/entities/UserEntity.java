package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "password") // toString excluding password
public class UserEntity {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Past
    private LocalDate birthDate;

    @NotBlank
    private String gender;

    @NotBlank
    @Pattern(regexp = "^[\\d+-]+$") // Pattern for phone numbers with optional dashes
    private String mobileNumber;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 8) // Enforce password length of at least 8 characters
    private String password; // Store password securely using a bcrypt encoder

    @NotBlank
    private String country;

    @NotBlank
    private String state;

    @NotBlank
    @Pattern(regexp = "^\\d{5}$") // Pattern for 5-digit zip code
    private String zip;
    
    private boolean isActive;

	public UserEntity(Long id, @NotBlank String firstName, @NotBlank String lastName, @Past LocalDate birthDate,
			@NotBlank String gender, @NotBlank @Pattern(regexp = "^[\\d+-]+$") String mobileNumber,
			@NotBlank @Email String email, @NotBlank String username, @NotBlank @Size(min = 8) String password,
			@NotBlank String country, @NotBlank String state, @NotBlank @Pattern(regexp = "^\\d{5}$") String zip,
			boolean isActive) {
		super();
		this.id = id; 
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.username = username;
		this.password = password;
		this.country = country;
		this.state = state;
		this.zip = zip;
		this.isActive = isActive;
	}

	
    
    
}
