package com.pearson.baristamatic.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pearson.baristamatic.entity.User;
import com.pearson.baristamatic.entity.User.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {
				"classpath:spring/applicationContext.xml", 
		"classpath:spring/hibernateContext.xml"})
public class UserServiceImplTest {

	@Autowired
	private UserService userService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		userService.clearUsers();
	}

	@Test
	public void testSaveOrUpdateUser() {
		User newUser = new User("jsmith", "password123", Role.CUSTOMER);
		userService.saveOrUpdateUser(newUser);
		User found = userService.findUser("jsmith");
		assertEquals(found.getUserName(), newUser.getUserName());
	}

	@Test
	public void testDeleteUser() {
		User newUser = new User("jsmith", "password123", Role.CUSTOMER);
		userService.saveOrUpdateUser(newUser);
		User found = userService.findUser("jsmith");
		assertEquals(found.getUserName(), newUser.getUserName());
		userService.deleteUser("jsmith");
		found = userService.findUser("jsmith");
		assertNull(found);
	}

	/*@Test
	This method is implicitly tested with testSaveOrUpdateUser()*/
	public void testFindUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindUsers() {
		userService.saveOrUpdateUser(new User("jsmith", "password123", Role.CUSTOMER));
		userService.saveOrUpdateUser(new User("jdoe", "password456", Role.CUSTOMER));
		assertEquals(userService.findUsers().size(), 2);
	}

	@Test
	public void testFindUsersInRole() {
		User customer = new User("jsmith", "password123", Role.CUSTOMER);
		userService.saveOrUpdateUser(customer);
		User admin = new User("Superman", "comics", Role.ADMINISTRATOR);
		userService.saveOrUpdateUser(admin);
		
		userService.findUsersInRole(Role.CUSTOMER).contains(customer);
		userService.findUsersInRole(Role.ADMINISTRATOR).contains(admin);
	}

	@Test
	public void testClearUsers() {
		userService.saveOrUpdateUser(new User("jsmith", "password123", Role.CUSTOMER));
		userService.saveOrUpdateUser(new User("jdoe", "password123", Role.CUSTOMER));
		assertEquals(userService.findUsers().size(), 2);
		userService.clearUsers();
		assertEquals(userService.findUsers().size(), 0);
	}

}
