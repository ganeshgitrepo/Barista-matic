package com.pearson.baristamatic.dao;

import java.util.List;

import com.pearson.baristamatic.entity.Role;
import com.pearson.baristamatic.entity.User;

public interface RoleDAO extends GenericDAO<Role, String> {
	public Role findRole(String roleType);
	public List<Role> findRoles();
	public List<User> findUsersInRole(String roleType);
	public void saveOrUpdateRole(Role role);
}