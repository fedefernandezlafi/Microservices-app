package com.microservices.usermicroservices.FeignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.microservices.usermicroservices.Models.Car;

@FeignClient(name = "car-service", url = "http://localhost:8002")
@RequestMapping("/car")
public interface CarFeignClient {
    @PostMapping()
    Car save(@RequestBody Car car);

    @GetMapping ("/byuser/{userId}")
    List <Car> getCars (@PathVariable long userId);

}
