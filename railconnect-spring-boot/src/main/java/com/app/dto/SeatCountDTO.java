package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SeatCountDTO {
    private Integer acCount;
    private Integer sleeperCount;
    private Integer generalCount;
    
  
}
