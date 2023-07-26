package com.thesis.springboot.vetclinicsystemcore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thesis.springboot.vetclinicsystemcore.models.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer>{
    
}
