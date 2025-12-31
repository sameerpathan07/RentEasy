package com.re.api.dao;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.re.api.dto.ImageRequest;
import com.re.api.entity.Image;

public interface ImageInterface {

    Image addImg(ImageRequest property, MultipartFile image)throws IOException;

    Image updateImg(int id,Image property, MultipartFile image)throws IOException;

    void deleteImg(int id);

    Optional<Image> viewImg(int id);

    List<Image> viewAllImgs();
}
