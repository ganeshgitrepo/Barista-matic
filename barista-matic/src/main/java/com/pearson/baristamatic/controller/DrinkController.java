package com.pearson.baristamatic.controller;

import com.pearson.baristamatic.entity.Drink;
import com.pearson.baristamatic.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value="/drink")
public class DrinkController {

    @Autowired
    private DrinkService drinkService;

    @RequestMapping(value="", method=RequestMethod.GET)
    public @ResponseBody List<Drink> showAllDrinks() {
        return drinkService.findDrinks();
    }

    @RequestMapping(value="/{drinkId}", method=RequestMethod.GET)
    public @ResponseBody Drink showDrink(@PathVariable long drinkId) {
        return drinkService.findDrink(drinkId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{drinkId}", method=RequestMethod.POST)
    public @ResponseBody String buyDrink(@PathVariable long drinkId) {
        boolean success = drinkService.buyDrink(drinkId);
        if (success)
            return "Purchased Drink: " + drinkService.findDrink(drinkId).getDrinkName();
        // else, throw an exception

    }
}