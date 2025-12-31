package com.re.api.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.re.api.dto.ImageRepo;
import com.re.api.dto.UserRepo;
import com.re.api.dto.WishListRepo;
import com.re.api.entity.WishList;

@Repository
public class WishListDao {
	
	@Autowired
	WishListRepo repo;
	
	 @Autowired
	 UserRepo userRepo;

	 @Autowired
	 ImageRepo imageRepo;

	public void addToWishlist( int propertyId, String userName) {

			if (repo.existsByUser_UserNameAndProperty_Id(userName, propertyId)) {
					return; // already liked
			}

			WishList w = new WishList();
			w.setUser(userRepo.findById(userName).get());
			w.setProperty(imageRepo.findById(propertyId).get());

			repo.save(w);
	}

	public List<WishList> getWishlist(@PathVariable String userName) {
	        return repo.findByUser_UserName(userName);
	    }
	

	public boolean isSaved(String userName, Long propertyId) {

	    return repo.existsByUser_UserNameAndProperty_Id(userName, propertyId);
	}

	
	 public void removeFromWishlist( int propertyId, String userName) {
	        
		 repo.deleteByUser_UserNameAndProperty_Id(userName, propertyId);
	    }
}
