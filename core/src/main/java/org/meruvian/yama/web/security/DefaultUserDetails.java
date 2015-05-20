
package org.meruvian.yama.web.security;

import java.util.Collection;

import org.meruvian.yama.core.user.User;
import org.springframework.security.core.GrantedAuthority;


public class DefaultUserDetails extends org.springframework.security.core.userdetails.User {
	private String id;
	private User user;
	
	public DefaultUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public DefaultUserDetails(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
