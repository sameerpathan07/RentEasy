package com.re.api.dao;

import java.util.Optional;

import com.re.api.dto.LoginRequest;
import com.re.api.dto.UpdateProfileRequest;
import com.re.api.entity.User;

public interface UserInterface {

	public User register(User user);
	public User login(LoginRequest login);
	public User update(UpdateProfileRequest user);
	public Optional<User> profile(String userName);
	public void delete(String userName);
	public void changePass(String userName, String oldPass, String newPass);
	
}
