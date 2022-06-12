package com.microservices.usermicroservices.Controller;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.microservices.usermicroservices.Entity.Users;
import com.microservices.usermicroservices.Models.Bike;
import com.microservices.usermicroservices.Models.Car;
import com.microservices.usermicroservices.Service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping ("/user")
public class UserController {

    @Autowired
    UserService userService;
   
    @GetMapping
    public ResponseEntity<List< Users>> getAll() {
        List<Users> users = userService.getAll();
        if (users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }
    
    @GetMapping ("/{id}")
    public ResponseEntity<Users> getById(@PathVariable ("id") long id) {
        Users user = userService.getUserById(id);
        if (user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping ()
    public ResponseEntity<Users> save(@RequestBody Users user) {
        userService.save(user) ;
        return ResponseEntity.ok(user);
    }

    @CircuitBreaker (name = "carsCB", fallbackMethod = "fallbackGetCars")
    @PostMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCars (@PathVariable ("userId") long userId){
        List<Car> cars = userService.getCars(userId);           
        return ResponseEntity.ok(cars);
    }

    @CircuitBreaker (name = "carsCB", fallbackMethod = "fallbackSaveCar")
    @PostMapping("/savecar/{userId}")
    public ResponseEntity<Car> saveCar (@PathVariable ("userId") long userId, @RequestBody Car car){
    Car carNew = userService.saveCar(car, userId);
    return ResponseEntity.ok(carNew);
    }
    @CircuitBreaker (name = "bikesCB", fallbackMethod = "fallbackGetBikes")
    @PostMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>> getBikes (@PathVariable ("userId") long userId){
        Users user = userService.getUserById(userId);
        
        List<Bike> bikes = userService.getBikes(userId);           
        return ResponseEntity.ok(bikes);
    }

    @CircuitBreaker (name = "bikesCB", fallbackMethod = "fallbackSaveBike")
    @PostMapping("/savebike/{userId}")
    public ResponseEntity<Bike> saveBike (@PathVariable ("userId") long userId, @RequestBody Bike bike){
    Bike bikeNew = userService.saveBike(bike, userId);
    return ResponseEntity.ok(bike);
    }

    @CircuitBreaker (name = "allCB", fallbackMethod = "fallbackGetAll")
    @GetMapping ("/getAll/{userId}")
    ResponseEntity <Map <String, Object>> getAll(@PathVariable ("userId") long userId) {
        Map <String, Object> result = userService.getUserCarsAndBikes(userId);
        return ResponseEntity.ok(result);
    }
    
    private ResponseEntity<String> fallbackGetCars (@PathVariable ("userId") long userId, RuntimeException e) {
        return ResponseEntity.ok("El usuario " + userId + "tiene los autos en el taller");
    }

    private ResponseEntity<String> fallbackSaveCar (@PathVariable ("userId") long userId, @RequestBody Car car, RuntimeException e ) {
        return ResponseEntity.ok("El usuario " + userId + "no tiene dinero para autos");
    }

    private ResponseEntity<String> fallbackGetBikes (@PathVariable ("userId") long userId, RuntimeException e) {
        return ResponseEntity.ok("El usuario " + userId + "tiene las motos en el taller");
    }

    private ResponseEntity<String> fallbackSaveBike (@PathVariable ("userId") long userId, @RequestBody Bike bike, RuntimeException e ) {
        return ResponseEntity.ok("El usuario " + userId + "no tiene dinero para motos");
    }

    private ResponseEntity<String> fallbackGetAll (@PathVariable ("userId") long userId, RuntimeException e) {
        return ResponseEntity.ok("El usuario " + userId + "tiene los vehiculos en el taller");
    }
    
}
