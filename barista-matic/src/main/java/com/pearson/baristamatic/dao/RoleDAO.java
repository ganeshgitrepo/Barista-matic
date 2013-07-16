package com.pearson.baristamatic.dao;

import java.util.List;

import com.pearson.baristamatic.entity.Role;

public interface RoleDAO extends GenericDAO<Role, String> {
	
	public void saveRole(Role role);
	public List<Role> findRoles(String roleName);

}
