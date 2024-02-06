package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
    private String firstName;
    private String lastName;
    
    @Lob
	private byte[] image;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING) // Use STRING for case-sensitivity
    @Column(name = "gender")
    private Gender gender;

    private String mobileNumber;
    private String email;
    private String username;
    private String password;
    private String country;
    private String state;
    private String zip;
    private boolean isActive;

	public UserEntity(Long id,String firstName,String lastName, LocalDate birthDate,
		    Gender gender, String mobileNumber,
			 String email,  String username, String password,
			String country,String state,String zip,
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

	public enum Gender {
        MALE, FEMALE, OTHER
    }
    
    
}
