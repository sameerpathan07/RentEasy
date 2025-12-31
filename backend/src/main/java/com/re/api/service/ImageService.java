package com.re.api.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.re.api.dao.ImageDao;
import com.re.api.dto.ImageRequest;
import com.re.api.entity.Image;

@Service
public class ImageService {

	@Autowired
	ImageDao dao;
	
	public Image addImg(ImageRequest property,MultipartFile image)throws IOException {
		return dao.addImg(property,image);
	}

	
	public Image updateImg(int id,Image property,MultipartFile image) throws IOException{
		return dao.updateImg(id, property,image);
	}

	
	public void deleteImg(int id) {
		 dao.deleteImg(id);
	}

	
	public Optional<Image> viewImg(int id) {
		return dao.viewImg(id);
	}

	
	public List<Image> viewAllImgs() {
		return dao.viewAllImgs();
	}
	
    public List<Image> getMyProperties( String userName) {
        return dao.getMyProperties(userName);
    }

}
