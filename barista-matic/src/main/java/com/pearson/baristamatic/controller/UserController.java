package com.pearson.baristamatic.controller;

import com.pearson.baristamatic.entity.User;
import com.pearson.baristamatic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;
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

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/auth", method=RequestMethod.POST)
    public @ResponseBody Map<String, String> getAuthenticatedUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userService.findUser(userName);
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", Long.toString(user.getUserId()));
        map.put("userName", user.getUserName());
        map.put("role", user.getRole().toString());
        return map;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value="", method= RequestMethod.POST)
    public @ResponseBody Map<String, String> registerUser(User user) {
        userService.saveOrUpdateUser(user);
        return getAuthenticatedUser();
    }

}