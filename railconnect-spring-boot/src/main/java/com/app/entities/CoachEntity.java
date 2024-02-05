package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "coaches")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CoachEntity {


    @Column(name = "first_class")
    private int firstClass;
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id", unique = true)
    private TrainEntity train;

    @Column(name = "second_class")
    private int secondClass;

    @Column(name = "sleeper")
    private int sleeper;
 
    @Column(name = "third_ac")
    private int thirdAC;

    @Column(name = "chair_car")
    private int chairCar;

	

	
}
