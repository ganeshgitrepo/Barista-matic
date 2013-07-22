package com.pearson.baristamatic.controller;

import com.pearson.baristamatic.entity.User;
import com.pearson.baristamatic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/{userId}", method=RequestMethod.GET)
    public @ResponseBody User showUser(@PathVariable long userId) throws IOException {
        User user = userService.findUser(userId);

        if (user == null)
            throw new IOException("The specified user does not exist.");
        else
            return user;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value="", method= RequestMethod.POST)
    public @ResponseBody Map<String, Long> registerUser(User user) {
        userService.saveOrUpdateUser(user);
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("Created user", user.getUserId());
        return map;
    }

}