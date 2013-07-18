package com.pearson.baristamatic.service;

import java.util.List;

import com.pearson.baristamatic.entity.Ingredient;

public interface IngredientService {
	
	/**
	 * Query for persistent objects in Ingredient class for an Ingredient with 
	 * the given name.				
	 * @param ingredientName	Name of Ingredient to search for.
	 * @return					Ingredient with the given name, otherwise null.
	 */
	public Ingredient findIngredient(String ingredientName);
	
	/**
	 * Return collection of all currently persisted Ingredient objects.
	 * @return					List of persisted Ingredient objects. May 
	 * 							return an empty list.
	 */
	public List<Ingredient> findIngredients();
	
	/**
	 * Persist the given Ingredient.
	 * @param ingredient		Ingredient object to persist.
	 */
	public void saveOrUpdateIngredient(Ingredient ingredient);
	
	/**
	 * Restock the inventory of the ingredient by incrementing the inventory 
	 * and purchases fields by the specified amount.
	 * @param ingredientName	Name of Ingredient to stock
	 * @param amount			Amount to increment inventory.
	 */
	public void restockIngredient(String ingredientName, int amount);
	
	/**
	 * Restock the inventory of all of the currently persisted Ingredients.
	 * @param amount			Amount to increment inventory.
	 */
	public void restockIngredients(int amount);
	
	/**
	 * Decrement the inventory of the given Ingredient. If the amount to 
	 * decrement the Ingredient exceeds the Ingredient's current inventory,
	 * the inventory will remain unchanged.
	 * @param ingredientName	Ingredient to be decremented
	 * @param amount			Amount to decrement ingredient inventory.
	 */
	void consumeIngredient(String ingredientName, int amount);
	
	
	/**
	 * Remove the persisted Ingredient with the given name.
	 * @param ingredientName		Name of Ingredient to be removed.
	 */
	public void deleteIngredient(String ingredientName);
	
	/**
	 * Remove all of the currently persisted Ingredients.
	 */
	public void clearIngredients();
}
