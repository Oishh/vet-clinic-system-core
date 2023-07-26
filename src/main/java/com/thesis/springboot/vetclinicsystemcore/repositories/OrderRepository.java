package com.thesis.springboot.vetclinicsystemcore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thesis.springboot.vetclinicsystemcore.models.OrderProduct;

public interface OrderRepository extends JpaRepository<OrderProduct, Integer>{
    
}
