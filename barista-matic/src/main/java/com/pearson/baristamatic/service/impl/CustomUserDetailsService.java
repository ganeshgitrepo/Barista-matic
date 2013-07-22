package com.pearson.baristamatic.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pearson.baristamatic.dao.UserDAO;
//import com.pearson.baristamatic.entity.User;
import com.pearson.baristamatic.entity.User.Role;

@Service
@Transactional(readOnly=true)
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDAO userDAO;

	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {
		
		com.pearson.baristamatic.entity.User baristaUser = userDAO.findUser(login);
		
		 /* boolean enabled = true;
		  boolean accountNonExpired = true;
		  boolean credentialsNonExpired = true;
		  boolean accountNonLocked = true;*/
		 
		
		return new User(baristaUser.getUserName(),
				        baristaUser.getPassword(),
				        getAuthorities(baristaUser.getRole()));
		
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Role role) {
		return getGrantedAuthorities(getRoles(role));
	}
	
	private List<String> getRoles(Role role){
		
		List<String> roles = new ArrayList<String>();

		if(role.equals(Role.CUSTOMER))
			roles.add("CUSTOMER");
		else
			roles.add("ADMINISTRATOR");
		return roles;
	}

		
	private List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

}
