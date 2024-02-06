package com.app.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainDTO {

    @NotNull(message = "Train number is required")
    @Positive(message = "Train number must be positive")
    private Integer trainNumber;

    @NotBlank(message = "Train name is required")
    @Size(max = 20, message = "Train name length must be less than or equal to 20 characters")
    private String trainName;

    @NotNull(message = "Arrival time is required")
    private LocalTime arrivalTime;

    @NotNull(message = "Departure time is required")
    private LocalTime departureTime;

    @NotNull(message = "Running date is required")
    private LocalDate runningDate;

    @Positive(message = "Base fare must be positive")
    private Double baseFare;

    private boolean activeStatus;

    private boolean cancelStatus;

    @NotNull(message = "Route ID is required")
    private Long routeId;

    private Map<String, String> classTypes;
}
