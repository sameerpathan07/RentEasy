package com.re.api.dto;

import java.time.LocalDate;

public class MailRequest {
	
	private String email;
	private  String address;
	private LocalDate meeting_date;
	private long phone_no;
	private String tenant_name;
	
	public MailRequest() {
		// TODO Auto-generated constructor stub
	}

	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getMeeting_date() {
		return meeting_date;
	}

	public void setMeeting_date(LocalDate meeting_date) {
		this.meeting_date = meeting_date;
	}

	public long getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(long phone_no) {
		this.phone_no = phone_no;
	}

	public String getTenant_name() {
		return tenant_name;
	}

	public void setTenant_name(String tenant_name) {
		this.tenant_name = tenant_name;
	}

	@Override
	public String toString() {
		return "MailRequest [email=" + email + ", address=" + address + ", meeting_date=" + meeting_date + ", phone_no="
				+ phone_no + ", tenant_name=" + tenant_name + "]";
	}
	
	

}
