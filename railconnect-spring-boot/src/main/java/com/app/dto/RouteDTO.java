package com.app.dto;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.app.entities.RouteEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteDTO {

    private Long routeId;

    @NotBlank(message = "Source is required")
    @Size(max = 20, message = "Source length must be less than or equal to 20 characters")
    private String source;

    @NotBlank(message = "Destination is required")
    @Size(max = 20, message = "Destination length must be less than or equal to 20 characters")
    private String destination;

//    @Size(max = 20, message = "Intermediate length must be less than or equal to 20 characters")
//    private String intermediate;
    
    
    public RouteEntity toEntity() {
        RouteEntity routeEntity = new RouteEntity();
//        routeEntity.setRouteId(this.routeId);
        routeEntity.setSource(this.source);
        routeEntity.setDestination(this.destination);
//        routeEntity.setIntermediate(this.intermediate);
        return routeEntity;
    }

}
