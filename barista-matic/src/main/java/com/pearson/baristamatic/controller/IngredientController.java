package com.pearson.baristamatic.controller;

import com.pearson.baristamatic.entity.Ingredient;
import com.pearson.baristamatic.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
        //return ingredientService.
        return null;
    }
}
