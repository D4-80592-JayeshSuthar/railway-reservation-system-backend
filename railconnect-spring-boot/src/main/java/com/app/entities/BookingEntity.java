	package com.app.entities;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.PastOrPresent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "bookings")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookingEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pnr_sequence_generator")
	@SequenceGenerator(name = "pnr_sequence_generator", initialValue = 10000)
	private Long pnrNumber;

	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<TicketEntity> tickets;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name = "coach_type")
	private Coaches coachType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id_fk")
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name = "train_number_fk")
	private TrainEntity train;
	
	@Column(name = "date_of_journey")
	@Future
	private LocalDate dateOfJourney;
	
	@Column
	@PastOrPresent
	private LocalDateTime bookingDateTime;
	
	@Column(length = 20, nullable = false)
	private String fromStation;
	
	@Column(length = 20, nullable = false)
	private String toStation;
	
	@Column(nullable = false)
	private Double totalAmount;
}
