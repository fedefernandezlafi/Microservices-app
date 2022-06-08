package com.microservices.bikemicroservices.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.bikemicroservices.Entity.Bike;
import com.microservices.bikemicroservices.Service.BikeService;


@RestController
@RequestMapping ("/bike")
public class BikeController {
    @Autowired
    BikeService bikeService;
   
    @GetMapping
    public ResponseEntity<List< Bike>> getAll() {
        List<Bike> bikes = bikeService.getAll();
        if (bikes.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(bikes);
    }
    
    @GetMapping ("/{id}")
    public ResponseEntity<Bike> getById(@PathVariable ("id") long id) {
        Bike bike = bikeService.getBikeById(id);
        if (bike == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bike);
    }

    
    @GetMapping ("/byuser/{userId}")
    public ResponseEntity<List<Bike>> getByUserId(@PathVariable ("userId") long userId) {
        List <Bike> bikes = bikeService.getBikeByUserId(userId);       
        return ResponseEntity.ok(bikes);
    }

    @PostMapping ()
    public ResponseEntity<Bike> save(@RequestBody Bike bike) {
        Bike bikeNew = bikeService.save(bike) ;
        return ResponseEntity.ok(bike);
    }


}
