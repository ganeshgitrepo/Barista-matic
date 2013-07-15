package com.pearson.baristamatic.dao;

import java.util.List;

import com.pearson.baristamatic.entity.User;

public interface UserDAO extends GenericDAO<User, String> {
	public void saveUser(User user);
	List<User> findUsers(String userName);
}
