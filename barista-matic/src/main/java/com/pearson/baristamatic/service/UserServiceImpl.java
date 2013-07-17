
package com.pearson.baristamatic.service;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pearson.baristamatic.dao.UserDAO;
import com.pearson.baristamatic.entity.User;
import com.pearson.baristamatic.entity.User.Role;

@Service("userService")
@Transactional(readOnly=true)
public class UserServiceImpl implements UserService {

	@Override
	public User findUser(String userName) {
		return userDAO.findUser(userName);
	}

	@Override
	public List<User> findUsers() {
		return userDAO.findUsers();
	}
	
	@Override
	public List<User> findUsersInRole(Role role) {
		return userDAO.findByCriteria(Restrictions.like("role", role));
	}

	
	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional(readOnly=false)
	public void saveOrUpdateUser(User user) {
		userDAO.saveOrUpdateUser(user);
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteUser(String userName) {
		User user = userDAO.findUser(userName);
		if (user != null) {
			userDAO.delete(user);
		}
	}

	@Override
	@Transactional(readOnly=false)
	public void clearUsers() {
		for (User u : findUsers()) {
			userDAO.delete(u);
		}
	}
}
