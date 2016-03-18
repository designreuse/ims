package com.ims.dto;

import java.util.List;

public class RoleTO extends AbstractTO {

	private static final long serialVersionUID = 1L;

	private String name;

	private String description;

	private List<PermissionTO> permission;

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

	public List<PermissionTO> getPermission() {
		return permission;
	}

	public void setPermission(List<PermissionTO> permission) {
		this.permission = permission;
	}

}
