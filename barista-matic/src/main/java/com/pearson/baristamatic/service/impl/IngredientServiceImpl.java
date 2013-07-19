package com.pearson.baristamatic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pearson.baristamatic.dao.IngredientDAO;
import com.pearson.baristamatic.entity.Ingredient;
import com.pearson.baristamatic.service.IngredientService;

@Service("ingredientService")
@Transactional(readOnly=true)
public class IngredientServiceImpl implements IngredientService {

	@Autowired
	private IngredientDAO ingredientDAO;

    @Override
    public Ingredient findIngredient(long ingredientId) {
        return ingredientDAO.findIngredient(ingredientId);
    }
	
	@Override
	public Ingredient findIngredient(String ingredientName) {
		return ingredientDAO.findIngredient(ingredientName);
	}

	@Override
	public List<Ingredient> findIngredients() {
		return ingredientDAO.findIngredients();
	}

	@Override
	@Transactional(readOnly=false)
	public void saveOrUpdateIngredient(Ingredient ingredient) {
		ingredientDAO.saveOrUpdateIngredient(ingredient);
	}

	@Override
	@Transactional(readOnly=false)
	public void restockIngredient(String ingredientName, int amount) {
		Ingredient ingredient = ingredientDAO.findIngredient(ingredientName);
		int newInventory = ingredient.getInventory() + amount;
		ingredient.setInventory(newInventory);
		int newPurchases = ingredient.getPurchases() + amount;
		ingredient.setPurchases(newPurchases);
		ingredientDAO.saveOrUpdateIngredient(ingredient);
	}

	@Override
	@Transactional(readOnly=false)
	public void restockIngredients(int amount) {
		List<Ingredient> ingredients = ingredientDAO.findIngredients();
		for (Ingredient i : ingredients) {
			int newInventory = i.getInventory() + amount;
			i.setInventory(newInventory);
			int newPurchases = i.getPurchases() + amount;
			i.setPurchases(newPurchases);
			ingredientDAO.saveOrUpdateIngredient(i);
		}

	}
	
	@Override
	@Transactional(readOnly=false)
	public void consumeIngredient(String ingredientName, int amount) {
		Ingredient ingredient = findIngredient(ingredientName);
		if (ingredient == null || ingredient.getInventory() < amount) {
			return;
		}
		
		int newAmount = ingredient.getInventory() - amount;
		ingredient.setInventory(newAmount);
		
		ingredientDAO.saveOrUpdate(ingredient);
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteIngredient(String ingredientName) {
		Ingredient ingredient = ingredientDAO.findIngredient(ingredientName);
		if (ingredient != null) {
			ingredientDAO.deleteIngredient(ingredientName);
		}
	}

	@Override
	@Transactional(readOnly=false)
	public void clearIngredients() {
		for (Ingredient i : findIngredients()) {
			ingredientDAO.deleteIngredient(i.getIngredientName());
		}
	}

}
