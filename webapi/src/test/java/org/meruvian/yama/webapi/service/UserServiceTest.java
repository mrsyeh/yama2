
package org.meruvian.yama.webapi.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.meruvian.yama.core.role.Role;
import org.meruvian.yama.core.user.User;
import org.meruvian.yama.webapi.Application;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
public class UserServiceTest {
	@Inject
	private UserService userService;
	
	@Test
	public void testInitializedUsers() {
		User user = userService.getUserByUsernameOrId("1");
		assertThat(user.getUsername(), is("administrator"));
		
		user = userService.getUserByUsernameOrId("administrator");
		assertThat(user.getId(), is("1"));
	}
	
	@Test
	public void testNumberOfInitializedUser() {
		Page<User> users = userService.findUserByKeyword("", null);
		assertThat(users.getTotalElements(), is(1L));
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void testUserValidation() {
		User user = new User();
		userService.saveUser(user);
		
		userService.findUserByKeyword("", null);
	}
	
	@Test
	public void testGetDefaultRole() {
		Page<Role> roles = userService.findRoleByUser("administrator", null);
		assertThat(roles.getTotalElements(), is(1L));
		assertThat(roles.getContent().get(0).getId(), is("1"));
	}
}
