package com.re.api.dto;

public class ChangePassRequest {

	private String userName;
	private String oldPass;
	private String newPass;
	
	public ChangePassRequest() {
		// TODO Auto-generated constructor stub
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	@Override
	public String toString() {
		return "ChangePassRequest [userName=" + userName + ", oldPass=" + oldPass + ", newPass=" + newPass + "]";
	}


	
	
	
	
}
