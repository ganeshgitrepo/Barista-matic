package com.pearson.baristamatic.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pearson.baristamatic.entity.Role;
import com.pearson.baristamatic.entity.User;

@Repository
public class RoleDAOImpl extends GenericDAOImpl<Role, String> implements RoleDAO {

	@Override
	public Role findRole(String roleType) {
		return findByCriteria(Restrictions.like("roleType", roleType)).get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findRoles() {
		return getCurrentSession().createQuery("from BARISTA_USER_ROLE").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUsersInRole(String roleType) {
		return getCurrentSession()
				.createQuery("from BARISTA_USER_ROLE as ROLE where ROLE.USER_NAME = ?")
				.setString(0, roleType)
				.list();
	}

	@Override
	public void saveOrUpdateRole(Role role) {
		saveOrUpdate(role);
	}
}
