package com.pearson.baristamatic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pearson.baristamatic.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
 
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public ResponseEntity<Void> getUsers(ModelMap model) {
 
		userService.findUsers("*");
		return null;
	}
}