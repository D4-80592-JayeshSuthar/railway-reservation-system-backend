package com.app.dto;

import com.app.entities.Gender;
import com.app.entities.PassengerEntity;
import com.app.entities.TicketStatus;

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
public class TicketDTO {
	private Long ticketId;
//	private Long pnrNumber; // Reference to Booking PNR instead of the entire BookingEntity
	private String seatNumber;
	private TicketStatus status;
//	private String passengerName;
//	private Integer passengerAge;
//	private String gender;
	private PassengerDTO passenger;
}
