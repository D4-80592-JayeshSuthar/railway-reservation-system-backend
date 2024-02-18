	package com.app.entities;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;

import com.app.dto.PassengerDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bookings")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@ToString
//@AttributeOverride(name = "id", column = @Column(name = "pnr_number"))
public class BookingEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pnr_sequence_generator")
	@SequenceGenerator(name = "pnr_sequence_generator", initialValue = 10000)
//	@Column(name = "pnr_number")
	private Long pnrNumber;

	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<TicketEntity> tickets;
	
//	//Ask Ghule Sir what to do
//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
//    private CoachEntity coach;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "coach_type")
	private Coaches coachType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id_fk")
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name = "train_number_fk")
	private TrainEntity train;
	

//	bcoz it can be computed using train_number
//	@Transient // Marks this property as not persistent
//	public String getSource() {
//		return train != null && train.getRoute() != null ? train.getRoute().getSource() : null;
//	}
//	bcoz it can be computed using train_number
//	// Example method to access destination, not stored directly in Booking table
//	@Transient // Marks this property as not persistent
//	public String getDestination() {
//		return train != null && train.getRoute() != null ? train.getRoute().getDestination() : null;
//	}

//	@OneToOne
//	@JoinColumn(name = "running_date", foreignKey = @ForeignKey(name = "fk_date_of_journey"))
//	private LocalDate dateOfJourney;

//	bcoz it can be computed using train_number
	
	@Column(name = "date_of_journey")
	@FutureOrPresent
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
