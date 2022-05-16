package com.se.ecofruits.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.se.ecofruits.dao.UserDAO;
import com.se.ecofruits.entity.User;

@Service
public class UserServiceImpl  implements UserService{

	@Autowired
	private UserDAO userDAO;
	@Override
	@Transactional
	public List<User> getUsers() {
		return userDAO.getUsers();
	}

	@Override
	@Transactional
	public void saveUser(User user) {
		userDAO.saveUser(user);
	} 

	@Override
	@Transactional
	public User getUser(int id) {
		return userDAO.getUser(id);
	}

	@Override
	@Transactional
	public void deleteUser(User user) {
		userDAO.deleteUser(user);
	}

	@Override
	@Transactional
	public User getUserbyPhone(String phone) {
		return userDAO.getUserbyPhone(phone);
	}

}
