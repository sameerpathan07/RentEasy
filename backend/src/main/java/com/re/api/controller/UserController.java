package com.re.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.re.api.dto.ChangePassRequest;
import com.re.api.dto.LoginRequest;
import com.re.api.dto.UpdateProfileRequest;
import com.re.api.entity.User;
import com.re.api.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService service;
	
	@PostMapping("/register")
	public User register(@RequestBody User user) {
		
		return service.register(user);
	}

	@PostMapping("/login")
	public User login(@RequestBody LoginRequest login) {
		return service.login(login);
	}
	
	@PutMapping("/update")
	public User update(@RequestBody UpdateProfileRequest user) {
		return service.update(user);
	}
	
	@PutMapping("/updatePass")
	public void changePass(@RequestBody ChangePassRequest cp) {
		
		 service.changePass(cp.getUserName(), cp.getOldPass(), cp.getNewPass());
	}
	
	@GetMapping("/profile/{userName}")
	public Optional<User> profile(@PathVariable String userName) {
		
		return service.profile(userName);
	}
	
	@DeleteMapping("/delete/{userName}")
	public void delete(@PathVariable String userName) {
		service.delete(userName);
		
	}

}
