package com.pearson.baristamatic.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pearson.baristamatic.entity.Role;
import com.pearson.baristamatic.entity.Role.RoleType;
import com.pearson.baristamatic.entity.User;

@Repository
public class RoleDAOImpl extends GenericDAOImpl<Role, String> implements RoleDAO {

	@Override
	public Role findRole(RoleType roleType) {
		return findByCriteria(Restrictions.like("roleType", roleType)).get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findRoles() {
		return getCurrentSession().createQuery("from Role").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUsersInRole(RoleType roleType) {
		return getCurrentSession()
				.createQuery("from Role where Role.userName = ?")
				.setString(0, roleType.toString())
				.list();
	}

	@Override
	public void saveOrUpdateRole(Role role) {
		saveOrUpdate(role);
	}

	@Override
	public void deleteRole(RoleType roleType) {
		Role role  = findRole(roleType);
		if (role != null)
			delete(role);
	}
}
