package com.microservices.usermicroservices.FeignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.microservices.usermicroservices.Models.Bike;


@FeignClient(name = "bike-service", url = "http://localhost:8003")
@RequestMapping("/bike")
public interface BikeFeignClient {

    @PostMapping()
    Bike save(@RequestBody Bike bike);

    @GetMapping ("/byuser/{userId}")
    List <Bike> getBikes (@PathVariable long userId);

}
