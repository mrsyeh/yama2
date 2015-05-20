
package org.meruvian.yama.core.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.meruvian.yama.core.DefaultPersistence;
import org.meruvian.yama.core.commons.Address;
import org.meruvian.yama.core.commons.FileInfo;
import org.meruvian.yama.core.commons.Name;
import org.meruvian.yama.core.role.UserRole;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "yama2showcase_backend_user")
public class User extends DefaultPersistence {
	private String username;
	private String password;
	private String email;
	private Name name = new Name();
	private Address address = new Address();
	private FileInfo fileInfo;
	private List<UserRole> roles = new ArrayList<UserRole>();
	
	@NotNull
	@Size(min = 6)
	@Column(name = "username", unique = true, nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotNull
	@Column(name = "email", unique = true, nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Embedded
	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}
	
	@Embedded
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@ManyToOne
	@JoinColumn(name = "file_info_id")
	public FileInfo getFileInfo() {
		return fileInfo;
	}
	
	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}
}