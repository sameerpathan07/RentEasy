package com.re.api.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {

	@Id
	private String userName;
	private String password;
	private String email;
	private String role;
	private String phoneNo;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user") 
	@JsonIgnore
	private List<Image> properties;

	
	public User() {
		// TODO Auto-generated constructor stub
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getPhoneNo() {
		return phoneNo;
	}


	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}


	public List<Image> getProperties() {
		return properties;
	}


	public void setProperties(List<Image> properties) {
		this.properties = properties;
	}


	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", email=" + email + ", role=" + role
				+ ", phoneNo=" + phoneNo + ", properties=" + properties + "]";
	}

	
	
	
	
}
