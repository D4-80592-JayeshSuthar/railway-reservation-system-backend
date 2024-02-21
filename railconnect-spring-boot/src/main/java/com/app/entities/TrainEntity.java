package com.app.entities;

import java.time.LocalTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "train_sequence_generator")
	@SequenceGenerator(name = "train_sequence_generator", initialValue = 1100)
	private long trainNumber;

	@Column(name = "train_name", nullable = false)
	private String trainName;

	@Column(nullable = false)
	private LocalTime arrivalTime;

	@Column(nullable = false)
	private LocalTime departureTime;


	@Column(nullable = false)
	private double baseFare;	

	private boolean activeStatus;

	private boolean cancelStatus;

	@OneToMany(mappedBy = "train", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<BookingEntity> bookings;

    @ManyToOne
    @JoinColumn(name = "route_id", referencedColumnName = "route_id")
    private RouteEntity route;

	private String runsOn;


	private Integer acSeats;


	private Integer sleeperSeats;


	private Integer generalSeats;


}
