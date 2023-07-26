package com.thesis.springboot.vetclinicsystemcore.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thesis.springboot.vetclinicsystemcore.exceptions.NotFoundException;
import com.thesis.springboot.vetclinicsystemcore.models.Veterinarian;
import com.thesis.springboot.vetclinicsystemcore.repositories.VeterinarianRepository;

import jakarta.validation.Valid;

@Service
public class VeterinarianService {
    private VeterinarianRepository veterinarianRepository;

    public VeterinarianService(VeterinarianRepository veterinarianRepository) {
        this.veterinarianRepository = veterinarianRepository;
    }


    public List<Veterinarian> getAllVeterinarians() {
        return veterinarianRepository.findAll();
    }

    public void updateVeterinarian(Integer id, @Valid Veterinarian veterinarianDetails) {
        Veterinarian updatedVeterinarian = veterinarianRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Veterinarian does not exist with id: " + id));

        updatedVeterinarian.setFirstname(veterinarianDetails.getFirstname());
        updatedVeterinarian.setLastname(veterinarianDetails.getLastname());
        updatedVeterinarian.setContactNumber(veterinarianDetails.getContactNumber());
        updatedVeterinarian.setSchedule(veterinarianDetails.getSchedule());


        veterinarianRepository.save(updatedVeterinarian);
    }

    public void deleteVeterinarian(Integer id) {
        veterinarianRepository.deleteById(id);
    }
}
