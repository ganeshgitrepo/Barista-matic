package com.pearson.baristamatic.service;

import java.util.List;
import java.util.Map;

import com.pearson.baristamatic.entity.Drink;
import com.pearson.baristamatic.entity.Ingredient;

public interface DrinkService {
	
	/**
	 * Query for persistent objects in Drink class for a Drink with the given Id.
	 * @param drinkId		Id of Drink to search for.
	 * @return				Drink with the given name, otherwise null.
	 */
	public Drink findDrink(long drinkId);
	
	/**
	 * Query for persistent objects in Drink class for a Drink with the given name.
	 * @param drinkName		Name of Drink to search for.
	 * @return				Drink with the given name, otherwise null.
	 */
	public Drink findDrink(String drinkName);
	
	
	/**
	 * Return collection of all currently persisted Drink objects.
	 * @return				List of persisted Drink objects. May return an 
	 * 						empty list.
	 */
	public List<Drink> findDrinks();
	
	/**
	 * Return a Map of ingredients present in the given Drink's recipe 
	 * as the key, with the number of parts as the value.
	 * @param drinkName		Name of drink to search for.
	 * @return				List of Ingredient objects that correspond to a drink 
	 * 						the given name.
	 */
	public Map<Ingredient, Integer> findIngredientsInDrink(String drinkName);
	
	/**
	 * Persist the given Drink.
	 * @param drink			Drink object to persist.
	 */
	public void saveOrUpdateDrink(Drink drink);

    /**
     * Buy a drink. This method decrements the inventory of Ingredients which
     * make up the Drink's recipe. May fail if the required Ingredient
     * inventory is too low.
     * @param drinkId       Id of the Drink to purchase
     * @return				True if drink purchase is successful, otherwise
     * 						false.
     */
    public boolean buyDrink(long drinkId);
	
	/**
	 * Buy a drink. This method decrements the inventory of Ingredients which
	 * make up the drink's recipe. May fail if the required Ingredient 
	 * inventory is too low.
	 * @param drinkName     Name of the Drink to purchase
	 * @return				True if drink purchase is successful, otherwise 
	 * 						false.
	 */
	public boolean buyDrink(String drinkName);
	
	/**
	 * Remove the persisted Drink with the given name.
	 * @param drinkName		Name of Drink to be removed.
	 */
	public void deleteDrink(String drinkName);
	
	/**
	 * Remove all of the currently persisted Drinks.
	 */
	public void clearDrinks();
}
