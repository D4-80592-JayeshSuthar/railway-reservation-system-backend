
package com.app.dto;

import com.app.entities.Gender;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassengerDTO {
    private String passengerName;
    private Gender gender;
    private Integer passengerAge;
}
