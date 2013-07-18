package com.pearson.baristamatic.service;

import java.util.List;

import com.pearson.baristamatic.entity.User;
import com.pearson.baristamatic.entity.User.Role;

public interface UserService {
	
	/**
	 * Query for persistent objects in User class for a User with the given 
	 * ID.
	 * @param userId			Id of User to search for.
	 * @return					User with the given name, otherwise null.
	 */
	
	public User findUser(long userId);
	
	/**
	 * Query for persistent objects in User class for a User with the given 
	 * name.
	 * @param userName			Name of User to search for.
	 * @return					User with the given name, otherwise null.
	 */
	public User findUser(String userName);
	
	/**
	 * Return collection of all currently persisted User objects.
	 * @return					List of persisted User objects. May return an
	 * 							empty list.
	 */
	public List<User> findUsers();
	
	/**
	 * Return collection of all currently persisted Users who belong to a given
	 * role.
	 * @param role				Role, either CUSTOMER or ADMINISTRATOR.
	 * @return					List of persisted User objects. May return an 
	 * 							empty list.
	 */
	public List<User> findUsersInRole(Role role);
	
	/**
	 * Persist the given user.
	 * @param user				User object to persist.
	 */
	public void saveOrUpdateUser(User user);
	
	/**
	 * Remove the persisted User with the given name.
	 * @param userName			Name of User to be removed.
	 */
	public void deleteUser(String userName);
	
	/**
	 * Remove all of the currently persisted Users.
	 */
	public void clearUsers();
}
