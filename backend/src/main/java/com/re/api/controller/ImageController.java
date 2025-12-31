package com.re.api.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import com.re.api.dto.ImageRequest;
import com.re.api.entity.Image;
import com.re.api.service.ImageService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/image")
public class ImageController {

	@Autowired
	ImageService service;
	
//	@PostMapping("/addImg")
//	public Image addImg(@RequestBody Image img) {
//		
//		return service.addImg(img);
//	}
	
	
	@PostMapping(value = "/addProp", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	
	public Image addProperty( @RequestPart("image") MultipartFile image, @RequestPart("property") ImageRequest property) throws IOException {

	    return service.addImg(property,image);
	}



//	@PutMapping("/updateImg/{id}")
//	public Image updateImg(@PathVariable int id, @RequestBody Image img) {
//		
//		return service.updateImg(id,img);
//	}
	
	@PutMapping(value = "/updateImg/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	
	public Image updateProperty(@PathVariable int id, @RequestPart(value = "image", required = false) MultipartFile image, @RequestPart("property") Image property
			) throws IOException {

	    
	    return service.updateImg(id, property,image);
	}


	@DeleteMapping("/deleteImg/{id}")
	public void deleteImg(@PathVariable int id) {
		
		 service.deleteImg(id);
	}

	@GetMapping("/getImg/{id}")
	public Optional<Image> viewImg(@PathVariable int id) {
		
		return service.viewImg(id);
	}

	@GetMapping("/getAllImg")
	public List<Image> viewAllImgs() {
		
		return service.viewAllImgs();
	}
	
	@GetMapping("/my/{userName}")
    public List<Image> getMyProperties(@PathVariable String userName) {
        return service.getMyProperties(userName);	
    }

}
