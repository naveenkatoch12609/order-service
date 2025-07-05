package com.warehouse.order_service;

import com.warehouse.order_service.entity.*;
import com.warehouse.order_service.repository.*;
import com.warehouse.order_service.service.OrderService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

	@Mock
	private OrderRepository orderRepo;

	@InjectMocks
	private OrderService orderService;

	private Order sampleOrder;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

	    OrderItem item = new OrderItem(null, "Product A", 2, 100.0, null);
	    List<OrderItem> itemList = new ArrayList<>();
	    sampleOrder = new Order(null, "John Doe", itemList, new Date());

	    item.setOrder(sampleOrder); // back-reference
	    itemList.add(item);
	}

	@Test
	void testCreateOrder() {
		when(orderRepo.save(any(Order.class))).thenReturn(sampleOrder);
		Order created = orderService.createOrder(sampleOrder);
		assertEquals("John Doe", created.getCustomerName());
		assertEquals(1, created.getItems().size());
		verify(orderRepo, times(1)).save(any(Order.class));
	}

	@Test
	void testGetAllOrders() {
		when(orderRepo.findAll()).thenReturn(List.of(sampleOrder));
		List<Order> orders = orderService.getAllOrders();
		assertEquals(1, orders.size());
		assertEquals("John Doe", orders.get(0).getCustomerName());
	}

	@Test
	void testGetOrderById() {
		when(orderRepo.findById(1L)).thenReturn(Optional.of(sampleOrder));
		Optional<Order> result = orderService.getOrderById(1L);
		assertTrue(result.isPresent());
		assertEquals("John Doe", result.get().getCustomerName());
	}

	@Test
	void testUpdateOrder() {
		Order updated = new Order(null, "Jane Smith", new ArrayList<>(), new Date());
		when(orderRepo.findById(1L)).thenReturn(Optional.of(sampleOrder));
		when(orderRepo.save(any(Order.class))).thenReturn(updated);

		Order result = orderService.updateOrder(1L, updated);
		assertEquals("Jane Smith", result.getCustomerName());
	}

	@Test
	void testDeleteOrder() {
		doNothing().when(orderRepo).deleteById(1L);
		orderService.deleteOrder(1L);
		verify(orderRepo, times(1)).deleteById(1L);
	}
}
