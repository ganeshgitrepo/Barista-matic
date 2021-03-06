package com.pearson.baristamatic.controller;

import com.pearson.baristamatic.entity.Ingredient;
import com.pearson.baristamatic.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value="/ingredient")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody
    List<Ingredient> showAllIngredients() {
        return ingredientService.findIngredients();
    }

    @RequestMapping(value = "/{ingredientId}")
    public @ResponseBody Ingredient showIngredient(@PathVariable long ingredientId) {
        return ingredientService.findIngredient(ingredientId);
    }

    @RequestMapping(value = "/{ingredientId}",  method=RequestMethod.PUT)
    public @ResponseBody Ingredient restockIngredient(@PathVariable long ingredientId, @RequestParam(value="amount",
            required = true) int amount) throws IOException {
        Ingredient ingredient = ingredientService.findIngredient(ingredientId);
        if (ingredient == null)
            throw new IOException("Could not locate ingredient.");
        else if (amount < 0)
            throw new IllegalStateException("Amount cannot be negative.");
        else {
            int newInventory = ingredient.getInventory() + amount;
            ingredient.setInventory(newInventory);
            int newPurchases = ingredient.getPurchases() + amount;
            ingredient.setPurchases(newPurchases);
            ingredientService.saveOrUpdateIngredient(ingredient);
            return ingredient;
        }
    }
}
