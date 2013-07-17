package com.pearson.baristamatic.service;

import java.util.List;

import com.pearson.baristamatic.entity.Role;
import com.pearson.baristamatic.entity.Role.RoleType;

public interface RoleService {
	public Role findRole(RoleType roleType);
	public List<Role> findRoles();
	public void saveOrUpdateRole(Role role);
	public void deleteRole(RoleType roleType);
	public void clearRoles();
}
