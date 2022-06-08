package com.microservices.carmicroservices.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.carmicroservices.Entity.Car;
import com.microservices.carmicroservices.Service.CarService;


@RestController
@RequestMapping ("/car")
public class CarController {
    @Autowired
    CarService carService;
   
    @GetMapping
    public ResponseEntity<List< Car>> getAll() {
        List<Car> cars = carService.getAll();
        if (cars.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(cars);
    }
    
    @GetMapping ("/id")
    public ResponseEntity<Car> getById(@PathVariable ("id") long id) {
        Car car = carService.getCarById(id);
        if (car == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(car);
    }

    
    @GetMapping ("/byuser/{userId}")
    public ResponseEntity<List<Car>> getByUserId(@PathVariable ("userId") long userId) {
        List <Car> cars = carService.getCarByUserId(userId);        
        return ResponseEntity.ok(cars);
    }

    @PostMapping ()
    public ResponseEntity<Car> save(@RequestBody Car car) {
        Car carNew = carService.save(car) ;
        return ResponseEntity.ok(car);
    }


}
