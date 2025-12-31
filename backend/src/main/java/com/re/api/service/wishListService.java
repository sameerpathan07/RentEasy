package com.re.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.re.api.dao.WishListDao;
import com.re.api.entity.WishList;

import jakarta.transaction.Transactional;

@Service
public class wishListService {
	
	@Autowired
	WishListDao dao;

	    public void addToWishlist(int propertyId, String userName) {
	    	
	    	dao.addToWishlist(propertyId, userName);
	    }

	 
	    public List<WishList> getWishlist(String userName) {
	        return dao.getWishlist(userName);
	    }
	

	    public boolean isSaved(String userName, Long propertyId) {

		    return dao.isSaved(userName, propertyId);
		    
		}
	    
	    @Transactional
	    public void removeFromWishlist( int propertyId, String userName) {
	        
	    	dao.removeFromWishlist(propertyId, userName);
	    }
}
