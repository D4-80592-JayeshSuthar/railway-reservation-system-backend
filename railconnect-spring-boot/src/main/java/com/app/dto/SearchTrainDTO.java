package com.app.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchTrainDTO {

	//fields existing in TrainEntity
	private Long trainNumber; 
    private String trainName;
    private LocalTime arrivalTime; //calculate the total journey time in frontend
    private LocalTime departureTime; //using arrivalTime - departureTime
    private Double baseFare;
//    private boolean activeStatus;
//    private boolean cancelStatus;
//    private Long routeId;
    private String runsOn;

    //fields other than TrainEntity
    private String source;
    private String destination;
    private Integer acSeats;  //Not max seats but the available seats 
    private Integer sleeperSeats; //on that particular date
    private Integer generalSeats; //so these are not TrainEntity fields
    private String dateOfJourney; //Not Using LocalDate
    
}
