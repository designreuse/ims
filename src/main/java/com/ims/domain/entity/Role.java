package com.ims.domain.entity;

import org.hibernate.envers.Audited;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;

@Entity
@Audited
@Table(name = "role")
public class Role extends AbstractEntity {

	private static final long serialVersionUID = 2887802770434501173L;

	private String name;

	private String description;

	private List<Permission> permission;

	private List<User> user;

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

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "role_permission_map", joinColumns = { @JoinColumn(name = "role_pkey") }, inverseJoinColumns = { @JoinColumn(name = "permission_pkey") })
	public List<Permission> getPermission() {
		return permission;
	}

	public void setPermission(List<Permission> permission) {
		this.permission = permission;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

}
