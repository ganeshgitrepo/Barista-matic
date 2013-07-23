package com.pearson.baristamatic.util;

import com.pearson.baristamatic.entity.User;
import com.pearson.baristamatic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestAuthenticationProvider implements AuthenticationProvider{

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userService.findUser(username);
        if (password.equals(user.getPassword())) {
            List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
            grantedAuthorityList.add(new SimpleGrantedAuthority(user.getRole().toString()));
            Authentication auth = new UsernamePasswordAuthenticationToken(username, password, grantedAuthorityList);
            return auth;
        } else
            return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}