package com.pearson.baristamatic.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;

public interface GenericDAO<T, I extends Serializable> {
	public T find(I id);
	public List<T> findByCriteria(Criterion criterion);
	public void saveOrUpdate(T t);
	public void delete(T t);
}
