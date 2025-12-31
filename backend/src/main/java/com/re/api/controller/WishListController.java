package com.re.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.re.api.dto.ImageRepo;
import com.re.api.dto.UserRepo;
import com.re.api.dto.WishListRepo;
import com.re.api.entity.WishList;
import com.re.api.service.wishListService;

@RestController
@RequestMapping("/wishlist")
@CrossOrigin(origins = "http://localhost:4200")
public class WishListController {

    @Autowired
    wishListService service;


    @PostMapping("/add/{propertyId}/{userName}")
    public void addToWishlist(@PathVariable int propertyId, @PathVariable String userName) {

    	service.addToWishlist(propertyId, userName);
    
    }

    @GetMapping("/{userName}")
    public List<WishList> getWishlist(@PathVariable String userName) {
        return service.getWishlist(userName);
    }
    
    @GetMapping("/exists/{userName}/{propertyId}")
    public boolean isSaved(
            @PathVariable String userName,
            @PathVariable Long propertyId) {

        return service.isSaved(userName, propertyId);
    }
    
    @DeleteMapping("/remove/{propertyId}/{userName}")
    public void removeFromWishlist( @PathVariable int propertyId, @PathVariable String userName) {
        
    	service.removeFromWishlist(propertyId, userName);
    }


}

