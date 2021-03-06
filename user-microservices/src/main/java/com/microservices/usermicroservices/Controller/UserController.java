package com.microservices.usermicroservices.Controller;

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
    
    @PostMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>> getBikes (@PathVariable ("userId") long userId){
        Users user = userService.getUserById(userId);
        
        List<Bike> bikes = userService.getBikes(userId);           
        return ResponseEntity.ok(bikes);
    }

     
    @PostMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCars (@PathVariable ("userId") long userId){
        List<Car> cars = userService.getCars(userId);           
        return ResponseEntity.ok(cars);
    }




    @PostMapping("/savecar/{userId}")
    public ResponseEntity<Car> saveCar (@PathVariable ("userId") long userId, @RequestBody Car car){
    Car carNew = userService.saveCar(car, userId);
    return ResponseEntity.ok(carNew);
    }

    
    @PostMapping("/savebike/{userId}")
    public ResponseEntity<Bike> saveBike (@PathVariable ("userId") long userId, @RequestBody Bike bike){
    Bike bikeNew = userService.saveBike(bike, userId);
    return ResponseEntity.ok(bike);
    }

    @GetMapping ("/getAll/{userId}")
    ResponseEntity <Map <String, Object>> getAll(@PathVariable ("userId") long userId) {
        Map <String, Object> result = userService.getUserCarsAndBikes(userId);
        return ResponseEntity.ok(result);
    }
}
