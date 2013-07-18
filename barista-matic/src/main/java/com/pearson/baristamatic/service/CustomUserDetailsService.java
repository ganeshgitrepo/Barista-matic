package com.pearson.baristamatic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pearson.baristamatic.dao.UserDAO;
import com.pearson.baristamatic.entity.User;

@Service
@Transactional(readOnly=true)
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDAO userDAO;

	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {
		
		User baristaUser = userDAO.findUser(login);
		
		 /* boolean enabled = true;
		  boolean accountNonExpired = true;
		  boolean credentialsNonExpired = true;
		  boolean accountNonLocked = true;*/
		 
		
		return (UserDetails) new User(baristaUser.getUserName(),
				        baristaUser.getPassword(),
				        baristaUser.getRole());
		
	}
	
/*	private String getRole(Role role){
		
		if(role.equals("CUSTOMER"))
			return "CUSTOMER";
		else
			return "ADMINISTRATOR";
	}*/

}
