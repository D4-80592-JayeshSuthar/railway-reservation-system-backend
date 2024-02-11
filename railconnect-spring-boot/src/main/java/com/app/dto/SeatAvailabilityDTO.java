package com.app.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SeatAvailabilityDTO {
	 private Long trainNumber;
	    private LocalDate dateOfJourney;
	    private int acCount;
	    private int sleeperCount;
	    private int generalCount;
}
