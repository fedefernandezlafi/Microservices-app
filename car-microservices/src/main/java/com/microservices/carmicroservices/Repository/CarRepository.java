package com.microservices.carmicroservices.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.carmicroservices.Entity.Car;

@Repository
public interface CarRepository extends JpaRepository <Car, Long>{
    List <Car> findByUserId (long userId);
    
}
