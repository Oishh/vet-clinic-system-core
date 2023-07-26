package com.thesis.springboot.vetclinicsystemcore.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thesis.springboot.vetclinicsystemcore.exceptions.NotFoundException;
import com.thesis.springboot.vetclinicsystemcore.models.OrderProduct;
import com.thesis.springboot.vetclinicsystemcore.models.OrderProduct.orderStatus;
import com.thesis.springboot.vetclinicsystemcore.repositories.OrderRepository;

import jakarta.validation.Valid;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderProduct> getAllOrders() {
        return orderRepository.findAll();
    }

    public void updateOrder(Integer id, @Valid OrderProduct orderDetails) {
        OrderProduct updatedOrder = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order does not exist with id: " + id));

        updatedOrder.setQuantity(orderDetails.getQuantity());
        updatedOrder.setSubTotal(orderDetails.getSubTotal());
        updatedOrder.setTotal(orderDetails.getTotal());


        orderRepository.save(updatedOrder);
    }

    public void confirmOrderStatus(Integer id) {
        OrderProduct updatedOrder = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order does not exist with id: " + id));

        updatedOrder.setStatus(orderStatus.COMPLETED);


        orderRepository.save(updatedOrder);
    }

    public void updateOrderNumber(Integer id, @Valid OrderProduct orderDetails) {
        OrderProduct updatedOrder = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order does not exist with id: " + id));

        updatedOrder.setOrderNumber(orderDetails.getOrderNumber());


        orderRepository.save(updatedOrder);
    }

    public void updateTimestamp(Integer id, @Valid OrderProduct orderDetails) {
        OrderProduct updatedOrder = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order does not exist with id: " + id));

        updatedOrder.setConfirmed_timestamp(orderDetails.getConfirmed_timestamp());


        orderRepository.save(updatedOrder);
    }

    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }
}
