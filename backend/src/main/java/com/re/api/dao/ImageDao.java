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
import com.re.api.dto.ImageRepo;
import com.re.api.dto.ImageRequest;
import com.re.api.dto.UserRepo;
import com.re.api.entity.Image;
import com.re.api.entity.User;

@Repository
public class ImageDao implements ImageInterface {

    @Autowired
    ImageRepo repo;

    @Autowired
    UserRepo repo2;

    @Autowired
    Cloudinary cloudinary; // ✅ Inject Cloudinary

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

        // ✅ UPLOAD TO CLOUDINARY
        // This takes the file bytes, uploads to cloud, and returns a Map of result data
        Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
        
        // Extract the public web URL
        String url = (String) uploadResult.get("url");
        
        img.setImageUrl(url); // Save the Cloudinary URL to DB

        return repo.save(img);
    }

    @Override
    public Image updateImg(int id, Image property, MultipartFile image) throws IOException {
        
        Image existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        // Update image ONLY if a new one is provided
        if (image != null && !image.isEmpty()) {
            // ✅ UPLOAD NEW IMAGE TO CLOUDINARY
            Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
            String url = (String) uploadResult.get("url");
            property.setImageUrl(url);
        } else {
            // Keep existing URL if no new image uploaded
            property.setImageUrl(existing.getImageUrl());
        }

        property.setId(id);
        return repo.save(property);
    }

	@Override
	public void deleteImg(int id) {
		
		repo.deleteById(id);
		
		//return "Image deleted Successfully";
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
