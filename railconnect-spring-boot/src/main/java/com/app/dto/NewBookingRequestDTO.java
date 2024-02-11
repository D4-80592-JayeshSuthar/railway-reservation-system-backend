package com.app.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewBookingRequestDTO {
    private Long userId;
    private String fromStation;
    private String toStation;
    private String coachType;
    private Long trainNumber;
    private LocalDateTime bookingDateTime;
    private LocalDate dateOfJourney;
    private List<PassengerDTO> passengers;
    private Integer totalAmount;
}
