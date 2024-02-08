//package com.app.entities;
//
//
//import java.time.LocalDate;
//
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//@Entity
//@Table(name = "bookings")
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@ToString
//public class BookingEntity {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pnr")
//	@SequenceGenerator(name = "pnr", initialValue = 23342783)
//	@Column(name = "pnr_number")
//	private Long pnrNumber;
//
//	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<TicketEntity> tickets;
//	
////	//Ask Ghule Sir what to do
////    @OneToOne(fetch = FetchType.LAZY, mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
////    private CoachEntity coach;
//	
//	@Enumerated(EnumType.STRING)
//	@Column(name = "coach")
//	private Coaches coach;
//
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "user_id")
//	private UserEntity user;
//
//	@ManyToOne
//	@JoinColumn(name = "train_number")
//	private TrainEntity train;
//
////	bcoz it can be computed using train_number
////	@Transient // Marks this property as not persistent
////	public String getSource() {
////		return train != null && train.getRoute() != null ? train.getRoute().getSource() : null;
////	}
////	bcoz it can be computed using train_number
////	// Example method to access destination, not stored directly in Booking table
////	@Transient // Marks this property as not persistent
////	public String getDestination() {
////		return train != null && train.getRoute() != null ? train.getRoute().getDestination() : null;
////	}
//
////	@OneToOne
////	@JoinColumn(name = "running_date", foreignKey = @ForeignKey(name = "fk_date_of_journey"))
////	private LocalDate dateOfJourney;
//
////	bcoz it can be computed using train_number
//	@Column(name = "date_of_journey")
//	private LocalDate dateOfJourney;
//	
//	@Column(length = 20)
//	private String fromStation;
//	
//	@Column(length = 20)
//	private String toStation;
//}


package com.app.entities;

import java.time.LocalDate;
import java.util.List;

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
public class BookingEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pnr")
    @SequenceGenerator(name = "pnr", initialValue = 23342783)
    @Column(name = "pnr_number")
    private Long pnrNumber;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TicketEntity> tickets;


    @Enumerated(EnumType.STRING)
    @Column(name = "coach")
    private Coaches coach;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "train_number")
    private TrainEntity train;

    @Column(name = "date_of_journey")
    private LocalDate dateOfJourney;

    @Column(length = 20)
    private String fromStation;

    @Column(length = 20)
    private String toStation;
}

