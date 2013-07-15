
package com.pearson.baristamatic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pearson.baristamatic.dao.UserDAO;
import com.pearson.baristamatic.entity.User;

@Service("userService")
@Transactional(readOnly=true)
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public User findByUserName(String userName) {
		return userDAO.findById(userName);
	}

	@Override
	@Transactional(readOnly=false)
	public void saveUser(User user) {
	userDAO.saveUser(user);
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteUser(String userName) {
		User user = userDAO.findById(userName);
		if (user != null) {
			userDAO.delete(user);
		}
	}

	@Override
	public List<User> findUsers(String user) {
		return userDAO.findUsers(user);
	}

}
