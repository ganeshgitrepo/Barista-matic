package com.pearson.baristamatic.dao.impl;

import com.pearson.baristamatic.dao.IngredientDAO;
import com.pearson.baristamatic.entity.Ingredient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IngredientDAOImpl extends GenericDAOImpl<Ingredient, Long> implements IngredientDAO {

    @Override
    public Ingredient findIngredient(long ingredientId) {
        return findById(ingredientId);
    }

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
    public void setInventory(long ingredientId, int amount) {
        getCurrentSession().createQuery("update Ingredient set inventory = :inventory where ingredientId = " +
                ":ingredientId")
                .setParameter("inventory", amount)
                .setParameter("ingredientId", ingredientId)
                .executeUpdate();
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
