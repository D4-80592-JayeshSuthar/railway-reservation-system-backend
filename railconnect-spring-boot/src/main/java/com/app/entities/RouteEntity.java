package com.app.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "routes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
//@AttributeOverride(name = "id", column = @Column(name = "route_id"))
public class RouteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "route_id")
	private Long routeId;


	@Column(length = 20)
	private String source;

	@Column(length = 20)
	private String destination;

//    @Column(length = 20)
//    private String intermediate;

}
