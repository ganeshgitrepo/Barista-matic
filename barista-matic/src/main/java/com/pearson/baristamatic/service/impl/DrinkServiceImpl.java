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
    public Map<Ingredient, Integer> findIngredientsInDrink(long drinkId) {
        return drinkDAO.findIngredientsInDrink(drinkId);
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

    @Override
    public boolean buyDrink(long drinkId) {
        Drink drink = drinkDAO.findDrink(drinkId);
        if (drink == null)
            return false;

        Map<Ingredient, Integer> ingredients = findIngredientsInDrink(drinkId);
        // iterate through ingredient Map and check required parts against current inventory
        for (Map.Entry<Ingredient, Integer> i : ingredients.entrySet()) {
            int part = i.getValue();

            System.err.println("Ingredient: " + i.getKey().getIngredientName());
            System.err.println("Inventory: " + i.getKey().getInventory());
            System.err.println("Parts required: " + part);

            if (i.getKey().getInventory() < part) {
                return false;
            }
        }
        // if we got here, we have enough ingredients in stock, so decrement inventory
        for (Map.Entry<Ingredient, Integer> i : ingredients.entrySet()) {
            Ingredient ingredient = i.getKey();
            int amount = i.getValue();
            int oldInventory = ingredient.getInventory();
            int newInventory = oldInventory - amount;
            ingredient.setInventory(newInventory);
            ingredientDAO.saveOrUpdateIngredient(ingredient);
            System.err.println("New " + i.getKey().getIngredientName() + "  inventory: " +
                    ingredientDAO.findIngredient(i.getKey().getIngredientName()));
        }

        drink.setSales(drink.getSales() + 1);
        System.err.println("Drink sales: " + drink.getSales());
        saveOrUpdateDrink(drink);
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