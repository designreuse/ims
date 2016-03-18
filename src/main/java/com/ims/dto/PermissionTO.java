package com.ims.dto;

public class PermissionTO extends AbstractTO {

	private static final long serialVersionUID = 8631845946059247733L;

	private String name;

	private String description;

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

}
