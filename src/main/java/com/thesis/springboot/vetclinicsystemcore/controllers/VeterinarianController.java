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
import com.thesis.springboot.vetclinicsystemcore.models.Veterinarian;
import com.thesis.springboot.vetclinicsystemcore.repositories.VeterinarianRepository;
import com.thesis.springboot.vetclinicsystemcore.services.VeterinarianService;

import jakarta.validation.Valid;

@CrossOrigin(origins={"*"})
@RestController
@RequestMapping("/api/v1/veterinarians")
public class VeterinarianController {
    private VeterinarianService veterinarianService;
    private VeterinarianRepository veterinarianRepository;

    public VeterinarianController(VeterinarianService veterinarianService, VeterinarianRepository veterinarianRepository) {
        this.veterinarianService = veterinarianService;
        this.veterinarianRepository = veterinarianRepository;
    }

    @GetMapping
    public List<Veterinarian> getAllVeterinarians() {
        return veterinarianService.getAllVeterinarians();
    }

    @GetMapping("{vetId}")
    public Veterinarian getVeterinarianById(@PathVariable Integer vetId) {
        Optional<Veterinarian> veterinarian = veterinarianRepository.findById(vetId);

        if(veterinarian.isEmpty()) {
            throw new NotFoundException("404 Not Found. Id: " +vetId);
        }

        return veterinarian.get();
    }

    @PostMapping
    public ResponseEntity<Veterinarian> createVeterinarian(@Valid @RequestBody Veterinarian veterinarian) {
        Veterinarian savedVeterinarian = veterinarianRepository.save(veterinarian);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedVeterinarian.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("{vetId}")
    public void updateVet(@PathVariable("vetId") Integer vetId, @RequestBody Veterinarian veterinarianDetails) {
      veterinarianService.updateVeterinarian(vetId, veterinarianDetails);
    }


    @DeleteMapping("{vetId}")
    public void deleteVeterinarianById(@PathVariable Integer vetId) {
        veterinarianService.deleteVeterinarian(vetId);
    }
}
