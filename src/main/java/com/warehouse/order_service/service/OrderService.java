package com.warehouse.order_service.service;

import com.warehouse.order_service.entity.Order;
import com.warehouse.order_service.entity.OrderItem;
import com.warehouse.order_service.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Transactional
    public Order createOrder(Order order) {
        for (OrderItem item : order.getItems()) {
            item.setOrder(order);
        }
        return orderRepo.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepo.findById(id);
    }

    @Transactional
    public Order updateOrder(Long id, Order updatedOrder) {
        return orderRepo.findById(id).map(existing -> {
            existing.setCustomerName(updatedOrder.getCustomerName());
            existing.getItems().clear();
            for (OrderItem item : updatedOrder.getItems()) {
                item.setOrder(existing);
                existing.getItems().add(item);
            }
            return orderRepo.save(existing);
        }).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public void deleteOrder(Long id) {
        orderRepo.deleteById(id);
    }
}