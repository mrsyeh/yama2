
package org.meruvian.yama.webapi.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.meruvian.yama.core.role.Role;
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
public class RoleServiceTest {
	@Inject
	private RoleService roleService;
	
	@Test
	public void testInitializedRoles() {
		Role role = roleService.getRoleById("1");
		assertThat(role.getName(), is("ADMINISTRATOR"));
	}
	
	@Test
	public void testNumberOfInitializedRole() {
		Page<Role> roles = roleService.findRoleByKeyword("", null);
		assertThat(roles.getTotalElements(), is(2L));
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void testRoleValidation() {
		Role role = new Role();
		roleService.saveRole(role);
		
		roleService.findRoleByKeyword("", null);
	}
}
