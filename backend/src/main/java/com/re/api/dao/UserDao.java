package com.re.api.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import com.re.api.dto.LoginRequest;
import com.re.api.dto.UpdateProfileRequest;
import com.re.api.entity.User;
import com.re.api.dto.UserRepo;

@Repository
public class UserDao implements UserInterface {

	@Autowired
	UserRepo repo;

	@Override
	public User register(User user) {

		Optional<User> user2 = repo.findById(user.getUserName());

		if (user2.isPresent()) {
			throw new RuntimeException("User already exists with username: " + user.getUserName());

		} else {
			return repo.save(user);
		}

	}

	@Override

	public User login(LoginRequest login) {
		Optional<User> user = repo.findByUserName(login.getUserName());

		if (user.isPresent()) {
			User foundUser = user.get();
			if (foundUser.getPassword().equals(login.getPassword())) {
				return foundUser;
			} else {
				throw new RuntimeException("Invalid password");
			}
		} else {
			throw new RuntimeException("User not found with username: " + login.getUserName());
		}
	}

	@Override
	public User update(UpdateProfileRequest user) {

		User user2 = repo.findById(user.getUserName()).orElseThrow(() -> new RuntimeException("User not found"));

		user2.setEmail(user.getEmail());
		user2.setPhoneNo(user.getPhoneNo());
		return repo.save(user2);

	}

	@Override
	public Optional<User> profile(String userName) {
		return repo.findById(userName);

	}

	@Override
	public void delete(String userName) {
		repo.deleteById(userName);

	}

	@Override
	public void changePass(String userName, String oldPass, String newPass) {

		User user = repo.findById(userName).orElseThrow(() -> new RuntimeException("User not found"));

		// 1️⃣ Validate existing password
		if (user.getPassword() == null || user.getPassword().isEmpty()) {
			throw new RuntimeException("Password not set for user");
		}

		// 2️⃣ Compare safely
		if (!user.getPassword().trim().equals(oldPass.trim())) {
			throw new RuntimeException("Old password incorrect");
		}

		// 3️⃣ Update password
		user.setPassword(newPass.trim());
		repo.save(user);
	}

}