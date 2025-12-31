package com.re.api.dto;

public class UpdateProfileRequest {

	private String userName;
	private String email;
	private String phoneNo;
	
	public UpdateProfileRequest() {
		// TODO Auto-generated constructor stub
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	@Override
	public String toString() {
		return "UpdateProfileRequest [userName=" + userName + ", email=" + email + ", phoneNo=" + phoneNo + "]";
	}
	
}
