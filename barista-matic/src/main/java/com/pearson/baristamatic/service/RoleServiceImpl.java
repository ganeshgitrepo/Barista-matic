package com.pearson.baristamatic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pearson.baristamatic.dao.RoleDAO;
import com.pearson.baristamatic.entity.Role;

@Service("roleService")
@Transactional(readOnly=true)
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDAO roleDAO;
	
	@Override
	public Role findByRoleName(String roleName) {
		return roleDAO.findById(roleName);
	}

	@Override
	@Transactional(readOnly=true)
	public void saveRole(Role role) {
		roleDAO.saveRole(role);
	}

	@Override
	@Transactional(readOnly=true)
	public void deleteRole(String roleName) {
		Role role = roleDAO.findById(roleName);
		if(role!=null)
			roleDAO.delete(role);
	}

	@Override
	public List<Role> findRoles(String role) {
		return roleDAO.findRoles(role);
	}

}
