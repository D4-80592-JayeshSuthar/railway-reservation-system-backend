package com.app.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
public class TrainEntity {

    @Id
    @Column(name = "train_number", unique = true)
    private int trainNumber;

    @Column(name = "train_name", length = 20)
    private String trainName;

    @Column(name = "arrival_time", length = 20)
    private LocalTime arrivalTime;

    @Column(name = "departure_time", length = 20)
    private LocalTime departureTime;

    @Column(name = "running_date")
    private LocalDate runningDate;

    @Column(name = "base_fare")
    private double baseFare;

    private boolean activeStatus;

    @Column(name = "cancel_status")
    private boolean cancelStatus;

    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookingEntity> bookings = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "route_id", foreignKey = @ForeignKey(name = "fk_train_route"))
    private RouteEntity route;

    @OneToOne(mappedBy = "train", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private CoachEntity coach;

    @Enumerated(EnumType.STRING)
    private Coaches coachType;

    @Column(name = "runs_on")
    private String runsOn;

    @Column(name = "schedule_link")
    private String scheduleLink;

    @Column(name = "departure_station")
    private String departureStation;

    @Column(name = "arrival_station")
    private String arrivalStation;

    @Column(name = "duration")
    private String duration;
}
