package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.RouteDAO;
import com.app.dto.RouteDTO;
import com.app.entities.RouteEntity;
import com.app.exceptions.ResourceNotFoundException;

@Service
public class RouteService {

    @Autowired
    private RouteDAO routeDAO;

    public RouteDTO getRouteById(Long routeId) {
        RouteEntity routeEntity = routeDAO.findById(routeId)
                                          .orElseThrow(() -> new ResourceNotFoundException("Route not found"));
        return convertToDTO(routeEntity);
    }
    
    public List<RouteDTO> getAllRoutes(){
    	List<RouteEntity> routeEntities = routeDAO.findAll();
    	List<RouteDTO> routeDTOs = new ArrayList<RouteDTO>();
    	for (RouteEntity routeEntity : routeEntities) {
			routeDTOs.add(convertToDTO(routeEntity));
		}
    	return routeDTOs;
    }

    private RouteDTO convertToDTO(RouteEntity routeEntity) {
        RouteDTO routeDTO = new RouteDTO();
        routeDTO.setRouteId(routeEntity.getRouteId());
        routeDTO.setSource(routeEntity.getSource());
        routeDTO.setDestination(routeEntity.getDestination());
//        routeDTO.setIntermediate(routeEntity.getIntermediate());
        return routeDTO;
    }

//    private List<RouteDto>
    // You can define other methods here for CRUD operations
}
