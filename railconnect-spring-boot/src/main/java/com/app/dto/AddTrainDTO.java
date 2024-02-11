package com.app.dto;

 
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddTrainDTO {
    // Existing fields
    private Long trainNumber; 
    private String trainName;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
//    private LocalDate runningDate;
    private Double baseFare;
    private boolean activeStatus;
    private boolean cancelStatus;
    private Long routeId;
//    private CoachDTO coachDTO; 
    private String runsOn;
    private Integer acSeats;
    private Integer sleeperSeats;
    private Integer generalSeats;
//    private String scheduleLink;
//    private String departureStation;
//    private String arrivalStation;
//    private String duration;
//    private Map<String, String> classTypes;
}
