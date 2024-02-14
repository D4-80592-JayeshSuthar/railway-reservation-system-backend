package com.app.entities;

import java.time.LocalTime;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "trains")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@ToString
//@AttributeOverride(name = "id", column = @Column(name = "train_number"))
public class TrainEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "train_sequence_generator")
	@SequenceGenerator(name = "train_sequence_generator", initialValue = 1100)
	private long trainNumber;

	@Column(name = "train_name", length = 20, nullable = false)
	private String trainName;

	@Column(nullable = false)
	private LocalTime arrivalTime;

	@Column(nullable = false)
	private LocalTime departureTime;

//    @Column(name = "running_date")
//    private LocalDate runningDate;

	@Column(name = "base_fare", nullable = false)
	private double baseFare;	

	private boolean activeStatus;

	private boolean cancelStatus;

	@OneToMany(mappedBy = "train", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<BookingEntity> bookings;

    @ManyToOne
    @JoinColumn(name = "route_id", foreignKey = @ForeignKey(name = "route_id_fk"))
    private RouteEntity route;

//	@ManyToOne
//	@JoinColumn(name = "route_id", referencedColumnName = "route_id")
//	private RouteEntity route;

//    @OneToOne(mappedBy = "train", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    private CoachEntity coach;

//    @Enumerated(EnumType.STRING)
//    private Coaches coachType;

	private String runsOn;


	private Integer acSeats;


	private Integer sleeperSeats;


	private Integer generalSeats;

//    @Column(name = "schedule_link")
//    private String scheduleLink;

//    @Column(name = "departure_station") 
//    private String departureStation;

//    @Column(name = "arrival_station")
//    private String arrivalStation;

//    @Column(name = "duration")
//    private String duration;
}
