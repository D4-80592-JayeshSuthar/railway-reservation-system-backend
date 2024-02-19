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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
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
	
	public UserEntity(LocalDate birthDate, String country, String email, String firstName, Gender gender, String lastName,
            String mobileNumber, String password, String state, String username, String zip, boolean isActive) {
        this.birthDate = birthDate;
        this.country = country;
        this.email = email;
        this.firstName = firstName;
        this.gender = gender;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.state = state;
        this.username = username;
        this.zip = zip;
        this.isActive = isActive;
    }

	
}
