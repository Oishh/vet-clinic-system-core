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
import com.thesis.springboot.vetclinicsystemcore.models.Patient;
import com.thesis.springboot.vetclinicsystemcore.repositories.PatientRepository;
import com.thesis.springboot.vetclinicsystemcore.services.PatientService;

import jakarta.validation.Valid;

@CrossOrigin(origins={"*"})
@RestController
@RequestMapping("api/v1/patients")
public class PatientController {
    
    private PatientService patientService;
    private PatientRepository patientRepository;

    public PatientController(PatientService patientService, PatientRepository patientRepository) {
        this.patientService = patientService;
        this.patientRepository = patientRepository;
    }


    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("{patientId}")
    public Patient getPatientById(@PathVariable("patientId") Integer id) {
        Optional<Patient> patient = patientRepository.findById(id);

        if(patient.isEmpty()) {
            throw new NotFoundException("404 Not Found. Id: " +id);
        }

        return patient.get();
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@Valid @RequestBody Patient patient) {
        Patient savedPatient = patientRepository.save(patient);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedPatient.getId()).toUri();

        return ResponseEntity.created(location).build();
    }



    @PutMapping("{patientId}")
    public void updatePatient(@PathVariable("patientId") Integer id, @RequestBody Patient patientDetails) {
      patientService.updatePatient(id, patientDetails);
    }


    @DeleteMapping("{patientId}")
    public void deletePatientById(@PathVariable("patientId") Integer id) {
        patientService.deletePatient(id);
    }


}
