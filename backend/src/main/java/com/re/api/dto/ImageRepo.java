package com.re.api.dto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.re.api.entity.Image;

public interface ImageRepo extends JpaRepository<Image, Integer> {

	List<Image> findByUser_UserNameOrderByIdDesc(String userName);
}
