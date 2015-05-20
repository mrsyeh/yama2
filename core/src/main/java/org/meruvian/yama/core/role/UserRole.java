
package org.meruvian.yama.core.role;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.meruvian.yama.core.DefaultPersistence;
import org.meruvian.yama.core.user.User;


@Entity
@Table(name = "yama2showcase_user_role", uniqueConstraints = @UniqueConstraint(columnNames = { "role_id", "user_id" }))
public class UserRole extends DefaultPersistence {
	private Role role = new Role();
	private User user = new User();
	
	public UserRole() {}

	public UserRole(Role role, User user) {
		this.role = role;
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
