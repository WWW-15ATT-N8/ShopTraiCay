package com.se.ecofruits.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.se.ecofruits.entity.Category;
import com.se.ecofruits.entity.Product;

@Repository
public class ProductDaoImpl implements ProductDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public List<Product> getProducts() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Product> query = currentSession.createQuery("from Products", Product.class);
		List<Product> products = query.getResultList();
		return products;
	}

	@Override
	public Product getProduct(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
        // now retrieve/read from database using the primary key
        Product product = currentSession.get(Product.class, id);
          return product;
	}

	@Override
	public void saveProduct(Product product) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(product);

	}

	@Override
	public void updateProduct(Product product) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(product);
	}

	@Override
	public void deleteProduct(Product product) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.delete(product);
	}

}
