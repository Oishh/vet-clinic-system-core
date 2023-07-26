package com.thesis.springboot.vetclinicsystemcore.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.thesis.springboot.vetclinicsystemcore.exceptions.NotFoundException;
import com.thesis.springboot.vetclinicsystemcore.models.Appointment;
import com.thesis.springboot.vetclinicsystemcore.repositories.AppointmentRepository;

import jakarta.validation.Valid;

@Service
public class AppointmentService {

    private AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public void createNewAppointment(LocalDate date, String time, String type, String status) {
        Appointment appointment = new Appointment();
        appointment.setDate(date);
        appointment.setTime(time);
        appointment.setType(type);
        appointment.setStatus(status);


        appointmentRepository.save(appointment);
    }

    public void updateAppointment(Integer id, @Valid Appointment appointmentDetails) {
        Appointment updatedAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Appointment does not exist with id: " + id));

        updatedAppointment.setDate(appointmentDetails.getDate());
        updatedAppointment.setTime(appointmentDetails.getTime());
        updatedAppointment.setType(appointmentDetails.getType());


        appointmentRepository.save(updatedAppointment);
    }

    public void toggleStatus(Integer id) {
        Appointment updatedAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Appointment does not exist with id: " + id));

        

        if(updatedAppointment.getStatus().equals("IN PROGRESS")) {
            updatedAppointment.setStatus("COMPLETED");
        } else {
            updatedAppointment.setStatus("IN PROGRESS");
        }


        appointmentRepository.save(updatedAppointment);
    }


    public void deleteAppointment(Integer id) {
        appointmentRepository.deleteById(id);
    }
    
}
