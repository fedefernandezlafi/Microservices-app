package com.microservices.usermicroservices.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.usermicroservices.Entity.Users;



@Repository
public interface UserRepository extends JpaRepository <Users, Long>{
    
}
