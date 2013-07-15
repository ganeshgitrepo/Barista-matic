package com.pearson.baristamatic.service;

import java.util.List;

import com.pearson.baristamatic.entity.User;

public interface UserService {
	public User findByUserName(String userName);
	public void saveUser(User user);
	public void deleteUser(String userName);
	List<User> findUsers(String user);
}
