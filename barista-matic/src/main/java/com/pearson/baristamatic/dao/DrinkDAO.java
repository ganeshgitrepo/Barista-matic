package com.pearson.baristamatic.dao;

import java.util.List;
import java.util.Map;

import com.pearson.baristamatic.entity.Drink;
import com.pearson.baristamatic.entity.Ingredient;

public interface DrinkDAO {
	public Drink findDrink(long drinkId);
	public Drink findDrink(String drinkName);
	public List<Drink> findDrinks();
	public Map<Ingredient, Integer> findIngredientsInDrink(String drinkName);
	public void saveOrUpdateDrink(Drink drink);
	public void deleteDrink(String drinkName);
}
