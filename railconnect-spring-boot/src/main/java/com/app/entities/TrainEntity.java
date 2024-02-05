package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "trains")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TrainEntity extends BaseEntity {

    @Column(name = "train_number", unique = true)
    private int trainNumber;
    
    @Column(name = "route_id", unique = true)
    private int routeId;

    @Column(name = "train_name", length = 20)
    private String trainName;

    @Column(name = "arrival_time", length = 20)
    private String arrivalTime;

    @Column(name = "departure_time", length = 20)
    private String departureTime;

    @Column(name = "running_date")
    private LocalDate runningDate;

    @Column(name = "base_fare")
    private double baseFare;
    
    @Column(name = "active_status")
    private boolean activeStatus;
    
    @Column(name = "cancel_status")
    private boolean cancelStatus;

    @OneToOne
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "route_id", foreignKey = @ForeignKey(name = "FK_Train_Route"))
    private CoachEntity coach;
}
