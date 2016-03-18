package com.ims.domain.entity;

import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Audited
@Table(name = "password")
public class Password extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	private String salt;

	private String hash;

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}


}
