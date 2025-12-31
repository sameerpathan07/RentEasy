package com.re.api.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

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

	
	    String uploadDir = System.getProperty("user.dir") + "/uploads/";
	    File dir = new File(uploadDir);
	    if (!dir.exists()) dir.mkdirs();

	    String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
	    Path path = Paths.get(uploadDir, fileName);
	    Files.copy(image.getInputStream(), path);

	    
	    img.setImageUrl("http://localhost:8091/uploads/" + fileName);

	    
	    return repo.save(img);
	}

	@Override
	public Image updateImg(int id,Image property, MultipartFile image)throws IOException {
		
		Image existing = repo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Property not found"));

	    // update image if new one uploaded
	    if (image != null && !image.isEmpty()) {
	        String uploadDir = System.getProperty("user.dir") + "/uploads/";
	        File dir = new File(uploadDir);
	        if (!dir.exists()) dir.mkdirs();

	        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
	        Path path = Paths.get(uploadDir + fileName);
	        Files.copy(image.getInputStream(), path);

	        property.setImageUrl("http://localhost:8091/uploads/" +fileName);
	    } else {
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
