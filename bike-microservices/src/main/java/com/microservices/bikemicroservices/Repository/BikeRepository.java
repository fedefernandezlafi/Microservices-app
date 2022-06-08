package com.microservices.bikemicroservices.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.bikemicroservices.Entity.Bike;

public interface BikeRepository extends JpaRepository <Bike, Long> {
    List <Bike> findByUserId (long userId);
}
