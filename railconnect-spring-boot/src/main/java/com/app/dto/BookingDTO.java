package com.app.dto;

import java.time.LocalDate;
import java.util.List;

import com.app.entities.Coaches;
//import com.app.entities.TicketEntity;

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
	private List<TicketDTO> tickets;
	private Coaches coach;
	private Long userId; 
	private Long trainNumber;
	private String from;
	private String to;
	private LocalDate dateOfJourney;

}
