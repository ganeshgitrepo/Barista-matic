package com.pearson.baristamatic.service.impl;

import java.util.List;
import java.util.Map;

import com.pearson.baristamatic.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pearson.baristamatic.dao.DrinkDAO;
import com.pearson.baristamatic.dao.IngredientDAO;
import com.pearson.baristamatic.entity.Drink;
import com.pearson.baristamatic.entity.Ingredient;

@Service("drinkService")
@Transactional(readOnly=true)
public class DrinkServiceImpl implements DrinkService {

	@Autowired
	private DrinkDAO drinkDAO;
	
	@Autowired
	private IngredientDAO ingredientDAO;
	
	@Override
	public Drink findDrink(long drinkId) {
		return drinkDAO.findDrink(drinkId);
	}
	
	@Override
	public Drink findDrink(String drinkName) {
		return drinkDAO.findDrink(drinkName);
	}

	@Override
	public List<Drink> findDrinks() {
		return drinkDAO.findDrinks();
	}

	@Override
	public Map<Ingredient, Integer> findIngredientsInDrink(String drinkName) {
		return drinkDAO.findIngredientsInDrink(drinkName);
	}

	@Override
	@Transactional(readOnly=false)
	public void saveOrUpdateDrink(Drink drink) {
		drinkDAO.saveOrUpdateDrink(drink);
	}

    /* Note: we are duplicating functionality because we have findIngredientsInDrink().
    we may want to refactor this if we have time later. */
    @Override
    public boolean buyDrink(long drinkId) {
        Drink drink = drinkDAO.findDrink(drinkId);

        if (drink == null)
            return false;

        Map<Ingredient, Integer> ingredients = findIngredientsInDrink(drink.getDrinkName());
        // iterate through ingredient Map and check required parts against current inventory
        for (Map.Entry<Ingredient, Integer> i : ingredients.entrySet()) {
            Ingredient ingredient = i.getKey();
            int part = i.getValue();

            if (ingredient.getInventory() < part) {
                return false;
            }
        }
        // if we got here, we have enough ingredients in stock, so decrement inventory
        for (Map.Entry<Ingredient, Integer> i : ingredients.entrySet()) {
            Ingredient ingredient = i.getKey();
            ingredient.setInventory(i.getValue());
            ingredientDAO.saveOrUpdate(ingredient);
        }

        drink.setSales(drink.getSales() + 1);
        return true;
    }

    @Override
	@Transactional(readOnly=false)
	public boolean buyDrink(String drinkName) {
		Drink drink = drinkDAO.findDrink(drinkName);
        return buyDrink(drink.getDrinkId());
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteDrink(String drinkName) {
		drinkDAO.deleteDrink(drinkName);
	}

	@Override
	@Transactional(readOnly=false)
	public void clearDrinks() {
		for (Drink d : findDrinks()) {
			drinkDAO.deleteDrink(d.getDrinkName());
		}
	}

}
