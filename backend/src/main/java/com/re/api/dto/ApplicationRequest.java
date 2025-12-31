package com.re.api.dto;

import java.time.LocalDate;

public class ApplicationRequest {

	private String name;
    private String email;
    private String phoneNo;
    private String address;
    private LocalDate date;
    private String tenant;
    private String landlord;
    
    public ApplicationRequest() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	
	
	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public String getLandlord() {
		return landlord;
	}

	public void setLandlord(String landlord) {
		this.landlord = landlord;
	}

	@Override
	public String toString() {
		return "ApplicationRequest [name=" + name + ", email=" + email + ", phoneNo=" + phoneNo + ", address=" + address
				+ ", date=" + date + ", tenant=" + tenant + ", landlord=" + landlord + "]";
	}
    
    
}
