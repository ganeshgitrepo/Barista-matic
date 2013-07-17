package com.pearson.baristamatic.dao;

import java.util.List;

import com.pearson.baristamatic.entity.Role;
import com.pearson.baristamatic.entity.Role.RoleType;
import com.pearson.baristamatic.entity.User;

public interface RoleDAO extends GenericDAO<Role, String> {
	public Role findRole(RoleType roleType);
	public List<Role> findRoles();
	public List<User> findUsersInRole(RoleType roleType);
	public void saveOrUpdateRole(Role role);
	public void deleteRole(RoleType roleType);
}