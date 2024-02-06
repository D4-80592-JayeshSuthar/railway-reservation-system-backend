package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "secure_users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Admin extends BaseEntity{

	@Column(length = 20)
	private String name;

	@Column(length = 300, nullable = false)
	private String password;

}
