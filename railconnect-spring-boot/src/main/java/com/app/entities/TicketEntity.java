package com.app.entities;

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
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tickets")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TicketEntity {

<<<<<<< HEAD:railconnect-spring-boot/src/main/java/com/app/entities/Tickets.java
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    @Column(name = "ticket_id")  
    private Long ticketId;
=======
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pnr_number")
	private BookingEntity booking;
	
	@Column(name = "seat_number", length = 5)
	private String seatNumber;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 10)
	private TicketStatus status;
	
	@Column(name = "passenger_name", length = 50)
	private String passengerName;
	
	@Column(name = "aadhar_number", length = 10)
	private Long aadharNumber;
}
>>>>>>> bf0cc9ecb379a6711e2ded41968f2f8808622dd7:railconnect-spring-boot/src/main/java/com/app/entities/TicketEntity.java

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pnr_number", referencedColumnName = "pnrNumber") // Assuming "pnrNumber" is the correct property in Bookings
    private Bookings booking;
    
    @Column(name = "seat_number", length = 5)
    private String seatNumber;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10)
    private TicketStatus status;
    
    @Column(name = "passenger_name", length = 50)
    private String passengerName;
    
    @Column(name = "aadhar_number", length = 10)
    private Long aadharNumber;
}
