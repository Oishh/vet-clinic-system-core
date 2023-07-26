package com.thesis.springboot.vetclinicsystemcore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thesis.springboot.vetclinicsystemcore.models.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    
}
