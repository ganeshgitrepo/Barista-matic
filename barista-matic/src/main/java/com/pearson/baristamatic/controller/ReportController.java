package com.pearson.baristamatic.controller;

import com.pearson.baristamatic.entity.Drink;
import com.pearson.baristamatic.entity.Ingredient;
import com.pearson.baristamatic.service.DrinkService;
import com.pearson.baristamatic.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/report")
public class ReportController {

	@Autowired
	private DrinkService drinkService;

	@Autowired
	private IngredientService ingredientService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/orders", method=RequestMethod.GET)
	public @ResponseBody Map<String, Integer> getDrinkOrders() {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		List<Drink> drinks = drinkService.findDrinks();
		for (Drink d : drinks) {
			map.put(d.getDrinkName(), d.getSales());
		}
		return map;
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/financials", method=RequestMethod.GET)
	public @ResponseBody Map<String, Double> getFinancials() {
		Map<String, Double> map = new LinkedHashMap<String, Double>();
		double revenue = 0.00;
		double expenses = 0.00;
		List<Drink> drinks = drinkService.findDrinks();
		for (Drink d : drinks) {
			revenue += (d.getSales() * d.getCost());
		}
		map.put("Revenue", revenue);

		List<Ingredient> ingredients = ingredientService.findIngredients();
		for (Ingredient i : ingredients) {
			expenses += (i.getPurchases() * i.getIngredientCost());
		}
		map.put("Expenses", expenses);

		// calculate total net profit
		double netProfit = revenue - expenses;
		map.put("Net Profit", netProfit);
		return map;
	}
}