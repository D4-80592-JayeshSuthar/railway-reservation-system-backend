package com.app.dto;
import com.app.entities.RefundReasons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter; 

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RefundDTO {
    private String trainName;
    private Long trainNumber;
    private Long ticketId;
    private RefundReasons reason; // Change the type to the enum
    private Double amount;
    private Boolean refundStatus;
}
