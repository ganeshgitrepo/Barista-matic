package com.pearson.baristamatic.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.pearson.baristamatic.dao.DrinkDAO;
import com.pearson.baristamatic.entity.Drink;
import com.pearson.baristamatic.entity.Ingredient;
import com.pearson.baristamatic.entity.Recipe;

@Repository
public class DrinkDAOImpl extends GenericDAOImpl<Drink, String> implements DrinkDAO {

	@Override
	public Drink findDrink(long drinkId) {
		return (Drink) getCurrentSession().get(getType(), drinkId);
	}

	@Override
	public Drink findDrink(String drinkName) {
		return (Drink) getCurrentSession()
				.createQuery("from Drink where drinkName = :drinkName")
				.setParameter("drinkName", drinkName)
				.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Drink> findDrinks() {
		return getCurrentSession().createQuery("from Drink").list();
	}

    /*
     * Because this method must query both the Recipe and Ingredient tables, it
     * is implemented in the DAO layer, and not the service layer.
     */
    @Override
    public Map<Ingredient, Integer> findIngredientsInDrink(long drinkId) {
        // find persisted drink
        Drink drink = findDrink(drinkId);
        if (drink == null)
            return null;
        // get a list of Recipe objects in the drink
        List<Recipe> recipeEntries = getCurrentSession()
                .createQuery("from Recipe where drink.drinkId = :drink")
                .setParameter("drink", drink.getDrinkId())
                .list();

        // convert the recipe objects to a list of ingredients
        Map<Ingredient, Integer> ingredients = new HashMap<Ingredient, Integer>();
        for (Recipe r : recipeEntries) {
            ingredients.put(r.getIngredient(), r.getPart());
        }

        return ingredients;
    }

	@SuppressWarnings("unchecked")
	@Override
	public Map<Ingredient, Integer> findIngredientsInDrink(String drinkName) {
        Drink drink = findDrink(drinkName);
        return findIngredientsInDrink(drink.getDrinkId());
	}

	@Override
	public void saveOrUpdateDrink(Drink drink) {
		saveOrUpdate(drink);
	}

	@Override
	public void deleteDrink(String drinkName) {
		Drink drink = findDrink(drinkName);
		if (drink != null)
			delete(drink);
	}

}
