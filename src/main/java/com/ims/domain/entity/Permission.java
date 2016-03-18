package com.ims.domain.entity;

import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Audited
@Table(name = "permission")
public class Permission extends AbstractEntity {

	private static final long serialVersionUID = 1169361744295995177L;

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
