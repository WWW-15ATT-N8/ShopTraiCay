package com.se.ecofruits.dao;

import java.util.List;

import com.se.ecofruits.entity.Order;
import com.se.ecofruits.entity.Order_Detail;

public interface OrderDetailDAO {
	public List<Order_Detail> getOrdersDetails();
	public void saveOrderDetail(Order_Detail order_Detail);
	public void updateOrderDetail(Order_Detail order_Detail);
	public void deleteOrderDetail(Order_Detail order_Detail);
}
