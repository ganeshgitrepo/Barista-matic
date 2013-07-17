package com.pearson.baristamatic.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pearson.baristamatic.entity.User;

@Repository
public class UserDAOImpl extends GenericDAOImpl<User, String> implements UserDAO {

	@Override
	public User findUser(String userName) {
		return findByCriteria(Restrictions.like("userName", userName)).get(0);
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