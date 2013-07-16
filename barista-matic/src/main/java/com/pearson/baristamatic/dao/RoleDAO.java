package com.pearson.baristamatic.dao;

import java.util.List;

import com.pearson.baristamatic.entity.Role;
import com.pearson.baristamatic.entity.User;

public interface RoleDAO extends GenericDAO<Role, String> {
	public Role getRole(String roleType);
	public List<Role> getRoles();
	public List<User> getUsersInRole(String roleType);
	public void saveRole(Role role);
}