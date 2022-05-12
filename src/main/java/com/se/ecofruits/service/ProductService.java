package com.se.ecofruits.service;

import java.util.List;

import com.se.ecofruits.entity.Product;

public interface ProductService {
	public List<Product> getProducts(); 
	public Product getProduct(int id);
 	public void saveProduct(Product product);
	public void updateProduct(Product product);
	public void deleteProduct(Product product);
}
