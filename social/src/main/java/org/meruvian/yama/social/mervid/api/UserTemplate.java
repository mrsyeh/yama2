
package org.meruvian.yama.social.mervid.api;

import org.meruvian.yama.core.user.User;
import org.springframework.web.client.RestTemplate;


public class UserTemplate implements UserOperations {
	private RestTemplate restTemplate;

	public UserTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Override
	public User getUser() {
		return restTemplate.getForObject("http://api.merv.id/v1/admin/users/me", User.class);
	}

}
