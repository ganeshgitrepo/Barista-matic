package com.pearson.baristamatic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pearson.baristamatic.entity.Drink;
import com.pearson.baristamatic.service.DrinkService;

@Controller
@RequestMapping(value="/drink")
public class DrinkController {

	@Autowired 
	private DrinkService drinkService;
	
	@RequestMapping(value="", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Drink> showDrinks() {
		//TODO Implement method to retrieve all drinks
		return null;
	}
	
	@RequestMapping(value="/{drinkId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Drink> showDrink(@PathVariable long drinkId) {
		Drink drink = drinkService.findDrink(drinkId);
		
		//TODO Handle null exception when no drink is found
		
		return new ResponseEntity<Drink>(drink, HttpStatus.OK);
	}
	
	@ExceptionHandler()
	ResponseEntity<String> handleBadRequest(Exception e) {
		return new ResponseEntity<String>("Drink does not exist", 
				HttpStatus.BAD_REQUEST);
	}
}