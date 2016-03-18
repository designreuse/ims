package com.ims.domain.entity;

import org.hibernate.envers.Audited;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import java.util.Calendar;

@Entity
@Audited
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "deletedFlag" }) })
public class User extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	private String name;

	private String firstName;

	private String lastName;

	private String email;

	private String contactNumber;

	private Calendar dob;

	private String department;

	private Password password;

	private Role role;

	private UserImage photo;

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Calendar getDob() {
		return dob;
	}

	public void setDob(Calendar dob) {
		this.dob = dob;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "password_pkey")
	public Password getPassword() {
		return password;
	}

	public void setPassword(Password password) {
		this.password = password;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_image_pkey")
	public UserImage getPhoto() {
		return photo;
	}

	public void setPhoto(UserImage photo) {
		this.photo = photo;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "role_pkey")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
