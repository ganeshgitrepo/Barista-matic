package com.pearson.baristamatic.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("unchecked")
public class GenericDAOImpl<T, I extends Serializable> implements GenericDAO<T, I> {

	@Autowired
	private SessionFactory sessionFactory;
	private Class<T> entityClass;

	public GenericDAOImpl() {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Class<T> getEntityClass() {
        return entityClass;
    }

	@Override
	public T findById(I id) {
		return (T) sessionFactory.getCurrentSession().get(getEntityClass(), id);
	}
	
	@Override
	public List<T> findByCriteria(Criterion criterion) {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
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
