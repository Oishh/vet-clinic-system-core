package com.thesis.springboot.vetclinicsystemcore.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thesis.springboot.vetclinicsystemcore.exceptions.NotFoundException;
import com.thesis.springboot.vetclinicsystemcore.models.Client;
import com.thesis.springboot.vetclinicsystemcore.repositories.ClientRepository;

import jakarta.validation.Valid;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public void updateClient(Integer id, @Valid Client clientDetails) {
        Client updatedClient = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client does not exist with id: " + id));

        updatedClient.setFullname(clientDetails.getFullname());
        updatedClient.setContactNumber(clientDetails.getContactNumber());
        updatedClient.setAddress(clientDetails.getAddress());

        clientRepository.save(updatedClient);
    }

    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }
    
}
