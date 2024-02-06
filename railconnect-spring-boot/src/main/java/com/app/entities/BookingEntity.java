package com.app.entities;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
<<<<<<< HEAD:railconnect-spring-boot/src/main/java/com/app/entities/Bookings.java
public class Bookings extends BaseEntity{
=======
public class BookingEntity {
>>>>>>> bf0cc9ecb379a6711e2ded41968f2f8808622dd7:railconnect-spring-boot/src/main/java/com/app/entities/BookingEntity.java
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pnr")
	@SequenceGenerator(name = "pnr", initialValue = 23342783)
	@Column(name = "pnr_number")
	private Long pnrNumber;

	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<TicketEntity> tickets = new HashSet<TicketEntity>();

//	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
//	private Set<CoachEntity> coaches = new HashSet<CoachEntity>();

	
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private CoachEntity coach;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<TrainEntity> trains = new HashSet<TrainEntity>();


	@ManyToOne
	@JoinColumn(name = "train_id")
	private TrainEntity train;

	
	
//	@Transient // Marks this property as not persistent
//	public String getSource() {
//		return train != null && train.getRoute() != null ? train.getRoute().getSource() : null;
//	}
//
//	// Example method to access destination, not stored directly in Booking table
//	@Transient // Marks this property as not persistent
//	public String getDestination() {
//		return train != null && train.getRoute() != null ? train.getRoute().getDestination() : null;
//	}

//	@OneToOne
//	@JoinColumn(name = "running_date", foreignKey = @ForeignKey(name = "fk_date_of_journey"))
//	private LocalDate dateOfJourney;

	
	@Column(name = "running_date")
	private LocalDate dateOfJourney;

}
