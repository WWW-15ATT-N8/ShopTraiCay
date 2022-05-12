package com.se.ecofruits.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.se.ecofruits.dao.ProductDao;
import com.se.ecofruits.entity.Product;

@Repository
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDAO;

	@Override
	@Transactional
	public Product getProduct(int id) {
		return productDAO.getProduct(id);
	}

	@Override
	@Transactional
	public List<Product> getProducts() {
		return productDAO.getProducts();
	}

	@Override
	@Transactional
	public void saveProduct(Product product) {
		productDAO.saveProduct(product);
	}

	@Override
	@Transactional
	public void updateProduct(Product product) {
		// TODO Auto-generated method stub
		productDAO.updateProduct(product);
	}

	@Override
	@Transactional
	public void deleteProduct(Product product) {
		// TODO Auto-generated method stub
		productDAO.deleteProduct(product);
	}

	

}
