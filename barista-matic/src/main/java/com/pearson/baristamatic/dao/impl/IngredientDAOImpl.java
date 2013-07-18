package com.pearson.baristamatic.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pearson.baristamatic.dao.IngredientDAO;
import com.pearson.baristamatic.entity.Ingredient;

@Repository
public class IngredientDAOImpl extends GenericDAOImpl<Ingredient, String> implements IngredientDAO {

	@Override
	public Ingredient findIngredient(String ingredientName) {
		return (Ingredient) getCurrentSession()
				.createQuery("from Ingredient where ingredientName = :ingredientName")
				.setParameter("ingredientName", ingredientName)
				.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Ingredient> findIngredients() {
		return getCurrentSession().createQuery("from Ingredient").list();
	}

	@Override
	public void saveOrUpdateIngredient(Ingredient ingredient) {
		saveOrUpdate(ingredient);
	}

	@Override
	public void deleteIngredient(String ingredientName) {
		Ingredient ingredient = findIngredient(ingredientName);
		if (ingredient != null)
			delete(ingredient);
	}
}
