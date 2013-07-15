package com.pearson.baristamatic.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pearson.baristamatic.entity.User;

@Repository
public class UserDAOImpl extends GenericDAOImpl<User, String> implements UserDAO {

	@Override
	public void saveUser(User user) {
		saveOrUpdate(user);
	}

	@Override
	public List<User> findUsers(String userName) {
		return findByCriteria(Restrictions.like("userName", userName, MatchMode.START));
	}

}
