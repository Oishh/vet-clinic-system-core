package com.thesis.springboot.vetclinicsystemcore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thesis.springboot.vetclinicsystemcore.models.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{
    

}
