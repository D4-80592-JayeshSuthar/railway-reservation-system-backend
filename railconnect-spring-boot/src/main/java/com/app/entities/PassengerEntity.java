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
@Table(name = "passengers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@AttributeOverride(name = "id",column = @Column(name="passenger_id",length = 10))
public class PassengerEntity extends BaseEntity{
	
	@Column(length = 30, nullable = false)
	private String passengerName;
	
	@Column(nullable = false)
	private Integer passengerAge;
	
}
