package com.pearson.baristamatic.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.List;

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
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml"})
public class UserServiceImplTest {

	@Autowired
	private UserService userService;

	@Before
	public void setUp() throws Exception {
		userService.clearUsers();
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

	/*@Test
	 * This method is implicitly tested with testSaveOrUpdate()
	 */
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
	 * This method is implicitly tested with testSaveOrUpdate()
	 */
	public void testFindUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindUsers() {
		userService.saveOrUpdateUser(new User("test.customer", "password123", Role.CUSTOMER));
		userService.saveOrUpdateUser(new User("test.admin", "password123", Role.ADMINISTRATOR));
		assertEquals(userService.findUsers().size(), 2);
	}

	@Test
	public void testFindUsersInRole() {
		User customer = new User("test.customer", "password123", Role.CUSTOMER);
		userService.saveOrUpdateUser(customer);
		List<User> users = userService.findUsersInRole(Role.CUSTOMER);
		assertEquals(users.get(0).getUserName(), "test.customer");
		
		User admin = new User("test.admin", "password123", Role.ADMINISTRATOR);
		userService.saveOrUpdateUser(admin);
		users = userService.findUsersInRole(Role.ADMINISTRATOR);
		assertEquals(users.get(0).getUserName(), "test.admin");
	}

	@Test
	public void testClearUsers() {
		User newUser = new User("jsmith", "password123", Role.CUSTOMER);
		userService.saveOrUpdateUser(newUser);
		assertEquals(userService.findUsers().size(), 1);
		userService.clearUsers();
		assertEquals(userService.findUsers().size(), 0);
	}
}
