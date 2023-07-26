package com.thesis.springboot.vetclinicsystemcore.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thesis.springboot.vetclinicsystemcore.exceptions.NotFoundException;
import com.thesis.springboot.vetclinicsystemcore.models.Inventory;
import com.thesis.springboot.vetclinicsystemcore.repositories.InventoryRepository;

import jakarta.validation.Valid;

@Service
public class InventoryService {
    
    private InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }


    public void updateInventory(Integer id, @Valid Inventory inventoryDetails) {
        Inventory updatedInventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Inventory does not exist with id: " + id));

        updatedInventory.setName(inventoryDetails.getName());
        updatedInventory.setCategory(inventoryDetails.getCategory());
        updatedInventory.setStock(inventoryDetails.getStock());
        updatedInventory.setPrice(inventoryDetails.getPrice());
        updatedInventory.setBase64Data(inventoryDetails.getBase64Data());


        inventoryRepository.save(updatedInventory);
    }

    public void updateStock(Integer id, @Valid Inventory inventoryDetails) {
        Inventory updatedInventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Inventory does not exist with id: " + id));

        updatedInventory.setStock(inventoryDetails.getStock());

        inventoryRepository.save(updatedInventory);
    }

    public void deleteInventory(Integer id) {
        inventoryRepository.deleteById(id);
    }
}
