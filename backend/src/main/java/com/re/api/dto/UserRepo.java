package com.re.api.dto;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.re.api.entity.User;

public interface UserRepo extends JpaRepository<User, String> {
	Optional<User> findByUserName(String userName);
}
