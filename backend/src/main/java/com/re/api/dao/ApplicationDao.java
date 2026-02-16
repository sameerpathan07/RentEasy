package com.re.api.dao;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.re.api.dto.ApplicationRepo; // ✅ Added
import com.re.api.dto.ImageRepo;
import com.re.api.dto.ImageRequest;
import com.re.api.dto.UserRepo;
import com.re.api.dto.WishListRepo; // ✅ Added
import com.re.api.entity.Application; // ✅ Added
import com.re.api.entity.Image;
import com.re.api.entity.User;
import com.re.api.entity.WishList; // ✅ Added

import jakarta.transaction.Transactional; // ✅ Added for safe deletion

@Repository
public class ImageDao implements ImageInterface {

    @Autowired
    ImageRepo repo;

    @Autowired
    UserRepo repo2;
    
    @Autowired
    ApplicationRepo appRepo; // ✅ Inject Application Repo
    
    @Autowired
    WishListRepo wishListRepo; // ✅ Inject WishList Repo

    @Autowired
    Cloudinary cloudinary;

    @Override
    public Image addImg(ImageRequest req, MultipartFile image) throws IOException {

        User owner = repo2.findByUserName(req.getUserName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Image img = new Image();
        img.setTitle(req.getTitle());
        img.setType(req.getType());
        img.setPrice(req.getPrice());
        img.setLocation(req.getLocation());
        img.setBedrooms(req.getBedrooms());
        img.setBathrooms(req.getBathrooms());
        img.setArea(req.getArea());
        img.setDescription(req.getDescription());
        img.setAvailable(req.isAvailable());

        img.setUser(owner);

        // Upload to Cloudinary
        Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
        String url = (String) uploadResult.get("url");
        
        img.setImageUrl(url);

        return repo.save(img);
    }

    @Override
    public Image updateImg(int id, Image property, MultipartFile image) throws IOException {
        
        Image existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        if (image != null && !image.isEmpty()) {
            Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
            String url = (String) uploadResult.get("url");
            property.setImageUrl(url);
        } else {
            property.setImageUrl(existing.getImageUrl());
        }

        property.setId(id);
        return repo.save(property);
    }

    @Override
    @Transactional // ✅ Important: Ensures all deletions happen together or fails together
    public void deleteImg(int id) {
        
        // 1. Delete associated WishList items first
        // Assuming WishList has a field 'property' mapping to Image
        List<WishList> wishLists = wishListRepo.findByProperty_Id(id);
        wishListRepo.deleteAll(wishLists);
        
        // 2. Delete associated Applications
        // Assuming Application has a field 'image' mapping to Image
        List<Application> applications = appRepo.findByImage_Id(id);
        appRepo.deleteAll(applications);
        
        // 3. Now it is safe to delete the Image
        repo.deleteById(id);
    }

    @Override
    public Optional<Image> viewImg(int id) {
        return repo.findById(id);
    }

    @Override
    public List<Image> viewAllImgs() {
        return repo.findAll();
    }

	
    public List<Image> getMyProperties(String userName) {
        return repo.findByUser_UserNameOrderByIdDesc(userName);
    }

}
