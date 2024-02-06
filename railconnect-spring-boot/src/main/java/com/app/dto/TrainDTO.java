package com.app.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import com.app.entities.Coaches;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainDTO {
    // Existing fields
    private Integer trainNumber;
    private String trainName;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    private LocalDate runningDate;
    private Double baseFare;
    private boolean activeStatus;
    private boolean cancelStatus;
    private Long routeId;
    private CoachDTO coachDTO; 

    // New fields
    private String runsOn;
    private String scheduleLink;
    private String departureStation;
    private String arrivalStation;
    private String duration;
    private Map<String, String> classTypes;
}
