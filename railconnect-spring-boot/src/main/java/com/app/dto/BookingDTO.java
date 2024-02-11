package com.app.dto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {	
	private Long pnrNumber;
    private List<PassengerDTO> passengers; // Add this field for passengers
	private List<TicketDTO> tickets;
	private String coachType;
	private Long userId; 
	private Long trainNumber;
	private String fromStation;
	private String toStation;
	private LocalDateTime bookingDateTime;
	private LocalDate dateOfJourney;
	private Double totalAmount;
//	private Integer seats;
//    private List<TicketDTO> seats; 
    
}