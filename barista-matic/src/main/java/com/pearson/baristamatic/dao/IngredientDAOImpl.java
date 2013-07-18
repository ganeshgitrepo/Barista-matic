package com.pearson.baristamatic.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pearson.baristamatic.entity.Ingredient;

@Repository
public class IngredientDAOImpl extends GenericDAOImpl<Ingredient, String> implements IngredientDAO {

	@Override
	public Ingredient findIngredient(String ingredientName) {
		List<Ingredient> ingredients = findByCriteria(Restrictions.like("ingredientName", ingredientName));
		
		if (ingredients.size() != 1)
			return null;
		
		return ingredients.get(0);
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
