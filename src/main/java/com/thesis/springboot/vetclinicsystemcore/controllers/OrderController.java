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
import com.thesis.springboot.vetclinicsystemcore.models.OrderProduct;
import com.thesis.springboot.vetclinicsystemcore.repositories.OrderRepository;
import com.thesis.springboot.vetclinicsystemcore.services.OrderService;

import jakarta.validation.Valid;

@CrossOrigin(origins={"*"})
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private OrderService orderService;
    private OrderRepository orderRepository;

    public OrderController(OrderService orderService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public List<OrderProduct> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("{orderId}")
    public OrderProduct getOrderById(@PathVariable("orderId") Integer id) {
        Optional<OrderProduct> order = orderRepository.findById(id);

        if(order.isEmpty()) {
            throw new NotFoundException("404 Not Found. Id: " +id);
        }

        return order.get();
    }


    @PostMapping
    public ResponseEntity<OrderProduct> createOrder(@Valid @RequestBody OrderProduct order) {
        OrderProduct savedOrder = orderRepository.save(order);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedOrder.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("{orderId}")
    public void updateOrder(@PathVariable("orderId") Integer id, @RequestBody OrderProduct orderDetails) {
      orderService.updateOrder(id, orderDetails);
    }

    @DeleteMapping("{orderId}")
    public void deleteOrderById(@PathVariable("orderId") Integer id) {
        orderService.deleteOrder(id);
    }

    @PutMapping("{orderId}/status")
    public void updateStatus(@PathVariable("orderId") Integer id) {
      orderService.confirmOrderStatus(id);
    }

    @PutMapping("{orderId}/order-num")
    public void updateOrderNumber(@PathVariable("orderId") Integer id, @RequestBody OrderProduct orderDetails) {
      orderService.updateOrderNumber(id, orderDetails);
    }

    @PutMapping("{orderId}/timestamp")
    public void updateTimestamp(@PathVariable("orderId") Integer id, @RequestBody OrderProduct orderDetails) {
      orderService.updateTimestamp(id, orderDetails);
    }
}
