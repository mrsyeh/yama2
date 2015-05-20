
package org.meruvian.yama.core.role;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.meruvian.yama.core.DefaultPersistence;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "yama2showcase_workflow_role")
public class Role extends DefaultPersistence {
	private String name;
	private String description;
	private List<UserRole> users = new ArrayList<UserRole>();
	
	@NotNull
	@Column(nullable = false, unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "role", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	public List<UserRole> getUsers() {
		return users;
	}

	public void setUsers(List<UserRole> users) {
		this.users = users;
	}
}
