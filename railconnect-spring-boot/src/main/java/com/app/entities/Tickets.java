package com.app.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Tickets {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pnr_number")
	private Bookings booking;
	private String seatNumber;
	private TicketStatus status;
	private String passengerName;
	private Long aadharNumber;
}
