package com.app.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.app.entities.Coaches;
//import com.app.entities.TicketEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingDTO {	
	private Long pnrNumber;
//    private List<PassengerDTO> passengers; // Add this field for passengers
	private Coaches coachType;
	private Long userId; 
	private Long trainNumber;
	private Set<TicketDTO> tickets;
	private String fromStation;
	private String toStation;
	private LocalDateTime bookingDateTime;
	private LocalDate dateOfJourney;
	private Double totalAmount;
//	private Integer seats;
}
