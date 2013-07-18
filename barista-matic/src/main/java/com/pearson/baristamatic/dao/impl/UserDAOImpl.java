package com.pearson.baristamatic.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pearson.baristamatic.dao.UserDAO;
import com.pearson.baristamatic.entity.User;

@Repository
public class UserDAOImpl extends GenericDAOImpl<User, String> implements UserDAO {

	@Override
	public User findUser(long userId) {
		return (User) getCurrentSession().get(getType(), userId);
	}
	
	@Override
	public User findUser(String userName) {
		return (User) getCurrentSession()
				.createQuery("from User where userName = :userName")
				.setParameter("userName", userName)
				.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> findUsers() {
		return getCurrentSession().createQuery("from User").list();
	}
	
	@Override
	public void saveOrUpdateUser(User user) {
		saveOrUpdate(user);
	}

	@Override
	public void deleteUser(String userName) {
		User user = findUser(userName);
		if (user != null)
			delete(user);
	}

}