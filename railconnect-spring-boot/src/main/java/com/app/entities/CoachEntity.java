package com.app.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class CoachEntity extends BaseEntity {

    @Column(name = "first_class")
    private int firstClass;

    @OneToOne(mappedBy = "coach", cascade = CascadeType.ALL, orphanRemoval = true)
    private Bookings booking;

    @Column(name = "second_class")
    private int secondClass;

    @Column(name = "sleeper")
    private int sleeper;

    @Column(name = "third_ac")
    private int thirdAC;

    @Column(name = "chair_car")
    private int chairCar;
}


