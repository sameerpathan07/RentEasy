package com.re.api.dto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.re.api.entity.Application;

@Repository
public interface ApplicationRepo extends JpaRepository<Application, Integer>{
	
	List<Application> findByTenant_UserNameOrderByCreatedAtDesc(String userName);

    List<Application> findByLandlord_UserNameOrderByCreatedAtDesc(String userName);
    
    boolean existsByImage_IdAndTenant_UserName(int imageId, String userName);

}
