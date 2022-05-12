package com.se.ecofruits.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.se.ecofruits.entity.Category;



@Repository
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public List<Category> getCategories() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Category> query = currentSession.createQuery("from Categories", Category.class);
		List<Category> categories = query.getResultList();
		return categories;
	}

	@Override
	public void saveCategory(Category cs) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(cs);
	}

	@Override
	public Category getCategory(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Category cs = currentSession.get(Category.class, id);
		return cs;
	}

	@Override
	public void deleteCategory(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("delete from category where id=:customerId");
		query.setParameter("customerId", id);
		query.executeUpdate();		
	}

}
