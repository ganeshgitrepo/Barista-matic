package com.pearson.baristamatic.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.pearson.baristamatic.dao.GenericDAO;

@SuppressWarnings("unchecked")
public class GenericDAOImpl<T, I extends Serializable> implements GenericDAO<T, I> {

	@Autowired
	private SessionFactory sessionFactory;
	private Class<T> type;

	public GenericDAOImpl() {
		this.type = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass())
				.getActualTypeArguments()[0];
	}
	
	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Class<T> getType() {
		return type;
    }

	@Transactional(readOnly = true)
	@Override
	public T find(I id) {
		return (T) sessionFactory
				.getCurrentSession()
				.get(getType(), id);
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<T> findByCriteria(Criterion criterion) {
		Criteria criteria = getCurrentSession().createCriteria(type);
        criteria.add(criterion);
        return criteria.list();
	}

	@Override
	public void saveOrUpdate(T t) {
		getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void delete(T t) {
		getCurrentSession().delete(t);
	}
}
