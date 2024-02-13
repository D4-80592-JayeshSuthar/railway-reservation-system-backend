package com.app.entities;

import java.time.LocalDate;

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

@Entity
@Table(name = "canceled_trains")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CanceledTrainEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "train_number")
    private Long trainNumber;
    
    @Column(name = "cancel_date")
    private LocalDate cancelDate;
    
    // Constructors, getters, and setters
}
