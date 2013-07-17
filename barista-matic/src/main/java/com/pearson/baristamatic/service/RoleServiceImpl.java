package com.pearson.baristamatic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pearson.baristamatic.dao.RoleDAO;
import com.pearson.baristamatic.entity.Role;
import com.pearson.baristamatic.entity.Role.RoleType;

@Service("roleService")
@Transactional(readOnly=true)
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDAO roleDAO;
	
	@Override
	public Role findRole(RoleType roleType) {
		return roleDAO.findRole(roleType);
	}
	
	@Override
	public List<Role> findRoles() {
		return roleDAO.findRoles();
	}

	@Override
	@Transactional(readOnly=false)
	public void saveOrUpdateRole(Role role) {
		roleDAO.saveOrUpdateRole(role);
	}

	@Override
	@Transactional(readOnly=true)
	public void deleteRole(RoleType roleType) {
		Role role = roleDAO.findRole(roleType);
		if(role!=null)
			roleDAO.delete(role);
	}

	@Override
	public void clearRoles() {
		for (Role r : findRoles()) {
			roleDAO.delete(r);
		}
	}
}
