package com.re.api.dto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.re.api.entity.WishList;

import jakarta.transaction.Transactional;

public interface WishListRepo extends JpaRepository<WishList, Integer> {


    List<WishList> findByUser_UserName(String userName);

    boolean existsByUser_UserNameAndProperty_Id(String userName, int propertyId);
    
    boolean existsByUser_UserNameAndProperty_Id(String userName, Long propertyId);
    
    @Modifying
    @Transactional
    void deleteByUser_UserNameAndProperty_Id(String userName, int propertyId);

}
