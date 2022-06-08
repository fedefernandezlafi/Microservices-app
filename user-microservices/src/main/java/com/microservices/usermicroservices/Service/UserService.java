package com.microservices.usermicroservices.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.weaver.patterns.BasicTokenSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.usermicroservices.Entity.Users;
import com.microservices.usermicroservices.FeignClients.BikeFeignClient;
import com.microservices.usermicroservices.FeignClients.CarFeignClient;
import com.microservices.usermicroservices.Models.Bike;
import com.microservices.usermicroservices.Models.Car;
import com.microservices.usermicroservices.Repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CarFeignClient carFeignClient;
    @Autowired
    BikeFeignClient bikeFeignClient;

    public List <Users> getAll() {
        return userRepository.findAll();
    }

    public Users getUserById (long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void save (Users user) {
        userRepository.save(user);
    }  

    public Car saveCar (Car car, long userId) {
        car.setUserId(userId);
        Car newCar = carFeignClient.save(car);
        return newCar;
    }

    
    public Bike saveBike (Bike bike, long userId) {
        bike.setUserId(userId);
        Bike newBike = bikeFeignClient.save(bike);
        return newBike;
    }

    public List<Bike> getBikes (long userId) {
        return bikeFeignClient.getBikes(userId);
    }
    public List<Car> getCars (long userId) {
        return carFeignClient.getCars(userId);
    }

    public Map <String, Object> getUserCarsAndBikes (long userId){
        Map <String, Object> result = new HashMap<>();
        Users user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            result.put("Mensaje", "User does not exist");
        return result;
        }
        result.put("User", user);
        List <Car> cars = carFeignClient.getCars(userId);
        if (cars.isEmpty()){
            result.put("Cars", "That user haven't any cars");
        } else {
            result.put("Cars", cars);
        }
           
        List <Bike> bikes = bikeFeignClient.getBikes(userId);
        if (bikes.isEmpty()){
            result.put("Bikes", "That user haven't any bikes");
        } else {
            result.put("Bikes", bikes);             
        }                  
        return result;
    }
}
