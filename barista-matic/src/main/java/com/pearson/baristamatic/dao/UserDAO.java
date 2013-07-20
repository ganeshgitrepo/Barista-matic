package com.pearson.baristamatic.dao;

import java.util.List;

import com.pearson.baristamatic.entity.User;

public interface UserDAO extends GenericDAO<User, Long> {
	public User findUser(long userId);
	public User findUser(String userName);
	public List<User> findUsers();
	public void saveOrUpdateUser(User user);
	public void deleteUser(String userName);
}
