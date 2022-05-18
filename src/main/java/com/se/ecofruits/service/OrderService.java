package com.se.ecofruits.service;

import java.util.List;

import com.se.ecofruits.entity.Order;

public interface OrderService {
	public List<Order> getOrders();
	public Order getOrder(int id);
	public void saveOrder(Order order);
	public void updateOrder(Order order);
	public void deleteOrder(Order order);
	public int getMaxID();
	public List<Order> getOrdersByDK(String dk);
}
