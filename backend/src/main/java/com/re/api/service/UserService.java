package com.re.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.re.api.dao.UserDao;
import com.re.api.dto.ChangePassRequest;
import com.re.api.dto.LoginRequest;
import com.re.api.dto.UpdateProfileRequest;
import com.re.api.entity.User;

@Service
public class UserService {

	@Autowired
	UserDao dao;
	
	
	public User register(User user) {
		
		return dao.register(user);
	}

	
	public User login(LoginRequest login) {
		
		return dao.login(login);
	}

	
	public User update(UpdateProfileRequest user) {
		
		return dao.update(user);
	}

	public Optional<User> profile(String userName) {
			
		return dao.profile(userName);
	}
	
	public void delete(String userName) {
		dao.delete(userName);
		
	}
	
	public void changePass( String userName, String oldPass, String newPass) {
		dao.changePass(userName,oldPass,newPass);
	}
}
