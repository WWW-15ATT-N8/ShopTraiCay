package com.se.ecofruits.service;

import java.util.List;

import com.se.ecofruits.entity.User;

public interface UserService {
	public List<User> getUsers();
	public void saveUser(User user);
	public User getUser(int id);
	public void deleteUser(User user);
	public User getUserbyPhone(String phone);
}
