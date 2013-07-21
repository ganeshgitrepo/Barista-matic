
package com.pearson.baristamatic.service.impl;

import com.pearson.baristamatic.dao.UserDAO;
import com.pearson.baristamatic.entity.User;
import com.pearson.baristamatic.entity.User.Role;
import com.pearson.baristamatic.service.UserService;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userService")
@Transactional(readOnly=true)
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public User findUser(long userId) {
		return userDAO.findUser(userId);
	}

	@Override
	public User findUser(String userName) {
		return userDAO.findUser(userName);
	}

	@Override
	public List<User> findUsers() {
		return userDAO.findUsers();
	}
	
	@Override
	public List<User> findUsersInRole(Role role) {
		return userDAO.findByCriteria(Restrictions.like("role", role));
	}
	
	@Override
	@Transactional(readOnly=false)
	public void saveOrUpdateUser(User user) {
		userDAO.saveOrUpdateUser(user);
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteUser(String userName) {
		User user = userDAO.findUser(userName);
		if (user != null) {
			userDAO.delete(user);
		}
	}

	@Override
	@Transactional(readOnly=false)
	public void clearUsers() {
		for (User u : findUsers()) {
			userDAO.deleteUser(u.getUserName());
		}
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUser(username);
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                getAuthorities(user.getRole()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Role role) {
        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
        return authList;
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
