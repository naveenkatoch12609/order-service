package com.warehouse.order_service.entity;

import jakarta.persistence.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Order {

	public Order() {
	}

	public Order(Long id, String customerName, List<OrderItem> items, Date createdAt) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.items = items;
		this.createdAt = createdAt;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String customerName;

	private String status;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<OrderItem> items = new ArrayList<>();

	private Date createdAt = new Date();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
