package com.pearson.baristamatic.service;

import java.util.List;

import com.pearson.baristamatic.entity.Role;

public interface RoleService {
	public Role findByRoleName(String roleName);
	public void saveRole(Role role);
	public void deleteRole(String roleName);
	public List<Role> findRoles(String role);
}
