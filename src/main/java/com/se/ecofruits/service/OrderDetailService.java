package com.se.ecofruits.service;

import java.util.List;

import com.se.ecofruits.entity.Order_Detail;

public interface OrderDetailService {
	public List<Order_Detail> getOrdersDetails();
	public void saveOrderDetail(Order_Detail order_Detail);
	public void updateOrderDetail(Order_Detail order_Detail);
	public void deleteOrderDetail(Order_Detail order_Detail);
	public List<Order_Detail> getOrdersDetailsByOrderID(int id);
}
