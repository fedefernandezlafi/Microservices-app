package com.microservices.bikemicroservices.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Bike {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private String brand;
    private String model;
    private long userId;


}
