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
import com.thesis.springboot.vetclinicsystemcore.models.Client;
import com.thesis.springboot.vetclinicsystemcore.models.Inventory;
import com.thesis.springboot.vetclinicsystemcore.models.OrderProduct;
import com.thesis.springboot.vetclinicsystemcore.models.Patient;
import com.thesis.springboot.vetclinicsystemcore.repositories.AppointmentRepository;
import com.thesis.springboot.vetclinicsystemcore.repositories.ClientRepository;
import com.thesis.springboot.vetclinicsystemcore.repositories.InventoryRepository;
import com.thesis.springboot.vetclinicsystemcore.repositories.OrderRepository;
import com.thesis.springboot.vetclinicsystemcore.repositories.PatientRepository;
import com.thesis.springboot.vetclinicsystemcore.services.ClientService;

import jakarta.validation.Valid;

@CrossOrigin(origins={"*"})
@RestController
@RequestMapping("api/v1/clients")
public class ClientController {
    
    private ClientService clientService;
    private ClientRepository clientRepository;
    private AppointmentRepository appointmentRepository;
    private PatientRepository patientRepository;
    private OrderRepository orderRepository;
    private InventoryRepository inventoryRepository;

    public ClientController(ClientService clientService, ClientRepository clientRepository, AppointmentRepository appointmentRepository, PatientRepository patientRepository, OrderRepository orderRepository, InventoryRepository inventoryRepository) {
        this.clientService = clientService;
        this.clientRepository = clientRepository;
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.orderRepository = orderRepository;
        this.inventoryRepository = inventoryRepository;
    }


    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("{clientId}")
    public Client getClientById(@PathVariable("clientId") Integer id) {
        Optional<Client> client = clientRepository.findById(id);

        if(client.isEmpty()) {
            throw new NotFoundException("404 Not Found. Id: " +id);
        }

        return client.get();
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client client) {
        Client savedClient = clientRepository.save(client);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedClient.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("{clientId}/appointments")
    public ResponseEntity<Appointment> createAppointmentForClient(@PathVariable("clientId") Integer id, @Valid @RequestBody Appointment appointment) {
        Optional<Client> client = clientRepository.findById(id);

        if(client.isEmpty()) {
            throw new NotFoundException("id:"+id);
        }

        appointment.setClient(client.get());

        Appointment savedAppointment = appointmentRepository.save(appointment);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedAppointment.getId())
                        .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("{clientId}/appointments")
    public List<Appointment> retrieveAppointmentsForClient(@PathVariable("clientId") Integer id) {
        Optional<Client> client = clientRepository.findById(id);

        if(client.isEmpty()) {
            throw new NotFoundException("id:"+id);
        }

        return client.get().getAppointments();
    }

    @PostMapping("{clientId}/patients")
    public ResponseEntity<Patient> createPatientForClient(@PathVariable("clientId") Integer id, @Valid @RequestBody Patient patient) {
        Optional<Client> client = clientRepository.findById(id);

        if(client.isEmpty()) {
            throw new NotFoundException("id:"+id);
        }

        patient.setClient(client.get());

        Patient savedPatient = patientRepository.save(patient);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedPatient.getId())
                        .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("{clientId}/patients")
    public List<Patient> retrievePatientsForClient(@PathVariable("clientId") Integer id) {
        Optional<Client> client = clientRepository.findById(id);

        if(client.isEmpty()) {
            throw new NotFoundException("id:"+id);
        }

        return client.get().getPatients();
    }

    @PostMapping("{clientId}/inventory/{inventoryId}/order")
    public ResponseEntity<OrderProduct> createOrderForClientWithInventory(@PathVariable("clientId") Integer clientId, @PathVariable("inventoryId") Integer inventoryId, @Valid @RequestBody OrderProduct order) {
        Optional<Client> client = clientRepository.findById(clientId);
        Optional<Inventory> inventory = inventoryRepository.findById(inventoryId);

        if(client.isEmpty()) {
            throw new NotFoundException("Client Id:"+clientId);
        } else if(inventory.isEmpty()) {
            throw new NotFoundException("Inventory Id:"+inventoryId);
        }

        order.setClient(client.get());
        order.setInventory(inventory.get());

        OrderProduct savedOrder = orderRepository.save(order);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedOrder.getId())
                        .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("{clientId}/order")
    public List<OrderProduct> retrieveOrderForClient(@PathVariable("clientId") Integer id) {
        Optional<Client> client = clientRepository.findById(id);

        if(client.isEmpty()) {
            throw new NotFoundException("id:"+id);
        }

        return client.get().getOrders();
    }

    @PutMapping("{clientId}/appointments/{appointmentId}")
    public void updateAppointmentByClient(@PathVariable("clientId") Integer clientId, @PathVariable("appointmentId") Integer appointmentId, @Valid @RequestBody Appointment appointment) {
        Optional<Client> client = clientRepository.findById(clientId);
        Appointment updatedAppointment = appointmentRepository.findById(appointmentId)
        .orElseThrow(() -> new NotFoundException("Appointment does not exist with id: " + appointmentId));

        if(client.isEmpty()) {
            throw new NotFoundException("id:"+clientId);
        }

        updatedAppointment.setClient(client.get());
        updatedAppointment.setDate(appointment.getDate());
        updatedAppointment.setType(appointment.getType());
        updatedAppointment.setTime(appointment.getTime());
        
        appointmentRepository.save(updatedAppointment);

    }

    @PutMapping("{clientId}/patients/{patientId}")
    public void updatePatientByClient(@PathVariable("clientId") Integer clientId, @PathVariable("patientId") Integer patientId, @Valid @RequestBody Patient patient) {
        Optional<Client> client = clientRepository.findById(clientId);
        Patient updatedPatient = patientRepository.findById(patientId)
        .orElseThrow(() -> new NotFoundException("Patient does not exist with id: " + patientId));

        if(client.isEmpty()) {
            throw new NotFoundException("id:"+clientId);
        }

        updatedPatient.setClient(client.get());
        updatedPatient.setName(patient.getName());
        updatedPatient.setAge(patient.getAge());
        updatedPatient.setBreed(patient.getBreed());
        updatedPatient.setBase64Data(patient.getBase64Data());
        
        patientRepository.save(updatedPatient);

    }



    @PutMapping("{clientId}")
    public void updateClient(@PathVariable("clientId") Integer id, @RequestBody Client clientDetails) {
      clientService.updateClient(id, clientDetails);
    }


    @DeleteMapping("{clientId}")
    public void deleteClientById(@PathVariable("clientId") Integer id) {
        clientService.deleteClient(id);
    }


}
