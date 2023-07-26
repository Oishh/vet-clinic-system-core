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
import com.thesis.springboot.vetclinicsystemcore.models.Inventory;
import com.thesis.springboot.vetclinicsystemcore.repositories.InventoryRepository;
import com.thesis.springboot.vetclinicsystemcore.services.InventoryService;

import jakarta.validation.Valid;

@CrossOrigin(origins={"*"})
@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private InventoryService inventoryService;
    private InventoryRepository inventoryRepository;

    public InventoryController(InventoryService inventoryService, InventoryRepository inventoryRepository) {
        this.inventoryService = inventoryService;
        this.inventoryRepository = inventoryRepository;
    }

    @GetMapping
    public List<Inventory> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @GetMapping("{inventoryId}")
    public Inventory getInventoryById(@PathVariable("inventoryId") Integer id) {
        Optional<Inventory> inventory = inventoryRepository.findById(id);

        if(inventory.isEmpty()) {
            throw new NotFoundException("404 Not Found. Id: " +id);
        }

        return inventory.get();
    }


    @PostMapping
    public ResponseEntity<Inventory> createInventory(@Valid @RequestBody Inventory inventory) {
        Inventory savedInventory = inventoryRepository.save(inventory);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedInventory.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("{inventoryId}")
    public void updateInventory(@PathVariable("inventoryId") Integer id, @RequestBody Inventory inventoryDetails) {
      inventoryService.updateInventory(id, inventoryDetails);
    }

    @PutMapping("{inventoryId}/stock")
    public void updateStock(@PathVariable("inventoryId") Integer id, @RequestBody Inventory inventoryDetails) {
      inventoryService.updateStock(id, inventoryDetails);
    }

    @DeleteMapping("{inventoryId}")
    public void deleteInventoryById(@PathVariable("inventoryId") Integer id) {
        inventoryService.deleteInventory(id);
    }
}
