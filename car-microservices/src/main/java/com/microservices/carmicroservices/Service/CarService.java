package com.microservices.carmicroservices.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.carmicroservices.Entity.Car;
import com.microservices.carmicroservices.Repository.CarRepository;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public List <Car> getAll() {
        return carRepository.findAll();
    }

    public Car getCarById (long id) {
        return carRepository.findById(id).orElse(null);
    }

    public Car save (Car car) {
        return carRepository.save(car);
    }

    public List <Car> getCarByUserId (long userId) {
        return carRepository.findByUserId(userId);
    }
}
