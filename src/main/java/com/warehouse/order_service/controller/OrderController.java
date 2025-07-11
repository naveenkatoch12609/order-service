package com.warehouse.order_service.controller;

import com.warehouse.order_service.entity.Order;
import com.warehouse.order_service.kafka.KafkaOrderProducer;
import com.warehouse.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
    private KafkaOrderProducer orderProducer;
	
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Order order) {
        // Set a unique order ID if not already present
        if (order.getId() == null) {
            order.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        }

        orderProducer.sendOrder(order);

        return ResponseEntity.accepted().body("Order accepted and will be processed asynchronously.");
    }

    @GetMapping("/status/{orderId}")
    public ResponseEntity<String> getOrderStatus(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId)
                .map(order -> ResponseEntity.ok(order.getStatus()))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public List<Order> getAll() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id) {
        return orderService.getOrderById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Order update(@PathVariable Long id, @RequestBody Order order) {
        return orderService.updateOrder(id, order);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
