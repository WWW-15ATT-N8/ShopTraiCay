package com.se.ecofruits.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.se.ecofruits.dao.OrderDetailDAO;
import com.se.ecofruits.entity.Order_Detail;
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
	
	@Autowired
	private OrderDetailDAO orderDetailDao;
	
	@Transactional
	@Override
	public List<Order_Detail> getOrdersDetails() {
		// TODO Auto-generated method stub
		return orderDetailDao.getOrdersDetails();
	}

	@Transactional
	@Override
	public void saveOrderDetail(Order_Detail order_Detail) {
		// TODO Auto-generated method stub
		orderDetailDao.saveOrderDetail(order_Detail);
	}

	@Transactional
	@Override
	public void updateOrderDetail(Order_Detail order_Detail) {
		// TODO Auto-generated method stub
		orderDetailDao.updateOrderDetail(order_Detail);
	}

	@Transactional
	@Override
	public void deleteOrderDetail(Order_Detail order_Detail) {
		// TODO Auto-generated method stub
		orderDetailDao.deleteOrderDetail(order_Detail);
	}
	
	@Transactional
	@Override
	public List<Order_Detail> getOrdersDetailsByProductId(int id) {
		// TODO Auto-generated method stub
		return orderDetailDao.getOrdersDetailsByProductId(id);
	}
	

}
