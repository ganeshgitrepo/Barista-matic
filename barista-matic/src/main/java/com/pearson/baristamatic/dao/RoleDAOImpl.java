package com.pearson.baristamatic.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pearson.baristamatic.entity.Role;

@Repository
public class RoleDAOImpl extends GenericDAOImpl<Role, String> implements RoleDAO {

	@Override
	public void saveRole(Role role) {
		saveOrUpdate(role);
	}

	@Override
	public List<Role> findRoles(String roleName) {
		return findByCriteria(Restrictions.like("roleName", roleName, MatchMode.START));
	}

}
