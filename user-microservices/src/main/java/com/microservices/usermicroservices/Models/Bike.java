package com.microservices.usermicroservices.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bike {
    private String brand;
    private String model;
    private long userId;
}
