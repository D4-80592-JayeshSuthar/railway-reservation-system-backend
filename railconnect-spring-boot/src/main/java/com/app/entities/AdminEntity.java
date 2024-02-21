package com.app.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column; 
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "admin")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "admin_id"))
public class AdminEntity extends BaseEntity{

	@Column(length = 20)
	private String userName;

	@Column(length = 300, nullable = false)
	private String password;

}
