package com.thesis.springboot.vetclinicsystemcore.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thesis.springboot.vetclinicsystemcore.exceptions.NotFoundException;
import com.thesis.springboot.vetclinicsystemcore.models.Patient;
import com.thesis.springboot.vetclinicsystemcore.repositories.PatientRepository;

import jakarta.validation.Valid;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        
        return patientRepository.findAll();
    }


    public void updatePatient(Integer id, @Valid Patient patientDetails) {
        Patient updatedPatient = patientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Patient does not exist with id: " + id));

        updatedPatient.setName(patientDetails.getName());
        updatedPatient.setAge(patientDetails.getAge());
        updatedPatient.setBreed(patientDetails.getBreed());
        updatedPatient.setGender(patientDetails.getGender());
        updatedPatient.setBase64Data(patientDetails.getBase64Data());

        patientRepository.save(updatedPatient);
    }

    public void deletePatient(Integer id) {
        patientRepository.deleteById(id);
    }

    
}
