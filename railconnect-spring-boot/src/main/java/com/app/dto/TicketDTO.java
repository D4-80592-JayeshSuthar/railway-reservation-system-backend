package com.app.dto;

import com.app.entities.Gender;
import com.app.entities.TicketStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
	private Long ticketId;
//	private Long pnrNumber; // Reference to Booking PNR instead of the entire BookingEntity
	private String seatNumber;
	private TicketStatus status;
//	private String passengerName;
//	private Integer passengerAge;
//	private String gender;
}
