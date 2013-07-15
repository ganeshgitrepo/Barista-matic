package com.pearson.baristamatic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pearson.baristamatic.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
 
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String printWelcome(ModelMap model) {
 
		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "index";
	}
}