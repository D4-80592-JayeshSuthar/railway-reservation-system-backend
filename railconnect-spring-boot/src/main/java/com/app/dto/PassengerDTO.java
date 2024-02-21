
package com.app.dto;

import com.app.entities.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PassengerDTO {
    private String passengerName;
    private Gender gender;
    private Integer passengerAge;
}
