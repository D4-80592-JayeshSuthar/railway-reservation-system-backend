package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class RouteEntity{

	@Id
    private long routeId;

    @Column(length = 20)
    private String source;

    @Column(length = 20)
    private String destination;

    @Column(length = 20)
    private String intermediate;
	
}
