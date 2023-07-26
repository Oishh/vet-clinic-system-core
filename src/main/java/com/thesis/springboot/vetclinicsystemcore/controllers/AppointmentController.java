package com.thesis.springboot.vetclinicsystemcore.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.thesis.springboot.vetclinicsystemcore.exceptions.NotFoundException;
import com.thesis.springboot.vetclinicsystemcore.models.Appointment;
import com.thesis.springboot.vetclinicsystemcore.repositories.AppointmentRepository;
import com.thesis.springboot.vetclinicsystemcore.services.AppointmentService;

import jakarta.validation.Valid;

@CrossOrigin(origins={"*"})
@RestController
@RequestMapping("api/v1/appointments")
public class AppointmentController {
    
    private AppointmentService appointmentService;
    private AppointmentRepository appointmentRepository;

    public AppointmentController(AppointmentService appointmentService, AppointmentRepository appointmentRepository) {
        this.appointmentService = appointmentService;
        this.appointmentRepository = appointmentRepository;
    }


    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@Valid @RequestBody Appointment appointment) {
        Appointment savedAppointment = appointmentRepository.save(appointment);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedAppointment.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("{appointmentId}")
    public Appointment getAppointmentById(@PathVariable("appointmentId") Integer id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);

        if(appointment.isEmpty()) {
            throw new NotFoundException("404 Not Found. Id: " +id);
        }

        return appointment.get();
    }


    @PutMapping("{appointmentId}")
    public void updateAppointment(@PathVariable("appointmentId") Integer id, @RequestBody Appointment appointmentDetails) {
      appointmentService.updateAppointment(id, appointmentDetails);
    }

    @PutMapping("{appointmentId}/status")
    public void toggleStatus(@PathVariable("appointmentId") Integer id) {
      appointmentService.toggleStatus(id);
    }


    @DeleteMapping("{appointmentId}")
    public void deleteAppointmentById(@PathVariable("appointmentId") Integer id) {
        appointmentService.deleteAppointment(id);
    }


}
