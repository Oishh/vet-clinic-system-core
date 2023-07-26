package com.thesis.springboot.vetclinicsystemcore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thesis.springboot.vetclinicsystemcore.models.Veterinarian;

public interface VeterinarianRepository extends JpaRepository<Veterinarian, Integer>{
    
}
