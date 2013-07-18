package com.pearson.baristamatic.dao;

import java.util.List;

import com.pearson.baristamatic.entity.Ingredient;

public interface IngredientDAO extends GenericDAO<Ingredient, String> {
	public Ingredient findIngredient(String ingredientName);
	public List<Ingredient> findIngredients();
	public void saveOrUpdateIngredient(Ingredient ingredient);
	public void deleteIngredient(String ingredientName);
}
