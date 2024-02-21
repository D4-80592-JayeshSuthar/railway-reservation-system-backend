package com.app.dto;

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
	private String seatNumber;
	private TicketStatus status;
	private PassengerDTO passenger;
}
