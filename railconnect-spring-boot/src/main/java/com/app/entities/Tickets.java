package com.app.entities;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

