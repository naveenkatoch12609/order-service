package com.warehouse.order_service;

import com.warehouse.order_service.entity.*;
import com.warehouse.order_service.repository.*;
import com.warehouse.order_service.service.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(OrderService.class) // Import your service manually since @DataJpaTest only loads Repos
public class OrderServiceStateTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    private Order sampleOrder;

    @BeforeEach
    void setUp() {
        OrderItem item = new OrderItem(null, "Product A", 2, 100.0, null);
        sampleOrder = new Order(null, "John Doe", new ArrayList<>(), new Date());

        item.setOrder(sampleOrder); // back-reference
        sampleOrder.getItems().add(item);
    }

    @Test
    void testCreateOrder_PersistsToDatabase() {
        Order saved = orderService.createOrder(sampleOrder);
        Optional<Order> fromDb = orderRepository.findById(saved.getId());

        assertTrue(fromDb.isPresent());
        assertEquals("John Doe", fromDb.get().getCustomerName());
        assertEquals(1, fromDb.get().getItems().size());
    }

    @Test
    void testGetOrderById_RetrievesFromDatabase() {
        Order saved = orderService.createOrder(sampleOrder);
        Optional<Order> fetched = orderService.getOrderById(saved.getId());

        assertTrue(fetched.isPresent());
        assertEquals(saved.getId(), fetched.get().getId());
    }

    @Test
    void testUpdateOrder_ChangesPersisted() {
        Order saved = orderService.createOrder(sampleOrder);
        Order changes = new Order(null, "Jane Smith", new ArrayList<>(), new Date());

        Order updated = orderService.updateOrder(saved.getId(), changes);
        assertEquals("Jane Smith", updated.getCustomerName());
    }

    @Test
    void testDeleteOrder_RemovesFromDatabase() {
        Order saved = orderService.createOrder(sampleOrder);
        orderService.deleteOrder(saved.getId());

        Optional<Order> deleted = orderRepository.findById(saved.getId());
        assertFalse(deleted.isPresent());
    }

    @Test
    void testGetAllOrders_ReturnsAll() {
        orderService.createOrder(sampleOrder);
        List<Order> all = orderService.getAllOrders();
        assertEquals(1, all.size());
    }
}

