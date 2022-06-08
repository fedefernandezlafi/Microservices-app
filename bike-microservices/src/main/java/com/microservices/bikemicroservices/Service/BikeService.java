package com.microservices.bikemicroservices.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.bikemicroservices.Entity.Bike;
import com.microservices.bikemicroservices.Repository.BikeRepository;

@Service
public class BikeService {
    @Autowired
    BikeRepository bikeRepository;

    public List <Bike> getAll() {
        return bikeRepository.findAll();
    }

    public Bike getBikeById (long id) {
        return bikeRepository.findById(id).orElse(null);
    }

    public Bike save (Bike bike) {
        return bikeRepository.save(bike);
    }

    public List <Bike> getBikeByUserId (long userId) {
        return bikeRepository.findByUserId(userId);
    }
}
