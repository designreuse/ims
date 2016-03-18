package com.ims.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ims.util.StripNonNumericStringJsonDeserializer;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

public class UserTO extends AbstractTO implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String name;

	private String firstName;

	private String lastName;

	private String email;

	@JsonDeserialize(using = StripNonNumericStringJsonDeserializer.class)
	private String contactNumber;

	private Calendar dob;

	private String department;
	
	private String password;

	private RoleTO role;

	private String actualImage;

	private String thumbnailView;

	private List<RoleTO> availableRoles;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getActualImage() {
		return actualImage;
	}

	public void setActualImage(String actualImage) {
		this.actualImage = actualImage;
	}

	public String getThumbnailView() {
		return thumbnailView;
	}

	public void setThumbnailView(String thumbnailView) {
		this.thumbnailView = thumbnailView;
	}

	public RoleTO getRole() {
		return role;
	}

	public void setRole(RoleTO role) {
		this.role = role;
	}

	public List<RoleTO> getAvailableRoles() {
		return availableRoles;
	}

	public void setAvailableRoles(List<RoleTO> availableRoles) {
		this.availableRoles = availableRoles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
