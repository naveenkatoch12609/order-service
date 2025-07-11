package com.warehouse.order_service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.warehouse.order_service.entity.Order;
import com.warehouse.order_service.service.OrderService;

@Service
public class KafkaOrderConsumer {

    @Autowired
    private OrderService orderService;

    @KafkaListener(topics = "order-topic", groupId = "order-group")
    public void consumeOrder(Order order) {
        System.out.println("Received Order: " + order);
        orderService.createOrder(order);
    }
}