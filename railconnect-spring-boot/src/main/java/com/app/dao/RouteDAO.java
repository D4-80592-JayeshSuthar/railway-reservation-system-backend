package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.RouteEntity;

public interface RouteDAO extends JpaRepository<RouteEntity, Long> {
    Optional<RouteEntity> findByRouteId(Long routeId);
    Optional<RouteEntity> findBySourceAndDestination(String source, String destination);
}
