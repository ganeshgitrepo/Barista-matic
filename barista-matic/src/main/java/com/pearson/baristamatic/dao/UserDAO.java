package com.pearson.baristamatic.dao;

import java.util.List;

import com.pearson.baristamatic.entity.User;

public interface UserDAO extends GenericDAO<User, String> {
	public User getUser(String userName);
	public List<User> getUsers();
	public void saveUser(User user);
	public void deleteUser(String userName);
}
