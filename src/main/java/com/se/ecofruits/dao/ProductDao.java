package com.se.ecofruits.dao;

import java.util.List;

import com.se.ecofruits.entity.Product;
import com.se.ecofruits.entity.Product;

public interface ProductDao {
	public List<Product> getProducts();
	public List<Product> getProductsFilter(String name, int categoryID, boolean newProduct, boolean bestSaler, String[] rangePrice, String[] rangeStock);
	public void saveProduct(Product product);
	public Product getProduct(int id);
	public void deleteProduct(int id);
}
