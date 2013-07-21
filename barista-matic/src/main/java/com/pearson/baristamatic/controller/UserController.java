package com.pearson.baristamatic.controller;

import com.pearson.baristamatic.entity.User;
import com.pearson.baristamatic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value="", method= RequestMethod.POST)
    public @ResponseBody Map<String, String> registerUser(User user) {
        userService.saveOrUpdateUser(user);
        Map<String, String> map = new HashMap();
        map.put("Created user", user.getUserName());
        return map;
    }

}