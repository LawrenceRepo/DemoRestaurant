package com.lawrence.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import com.lawrence.model.DishCategory;
import com.lawrence.model.Media;
import com.lawrence.repository.DishCategoryRepo;
import com.lawrence.repository.ProductRepo;
import com.lawrence.repository.RestaurantRepo;
import com.lawrence.service.DishCategoryService;
import com.lawrence.web.dto.DishCategoryDto;

public class DishCategoryServiceImpl implements DishCategoryService {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    DishCategoryRepo dishCategoryRepo;
    @Autowired
    RestaurantRepo restaurantRepo;
    @Value("${file.upload-dir}") // reading from default application properties file
    private String uploadPath;
    static final  String SUBDIRECTORY = "category/";
    
    @Override
    public DishCategory addDishCategory(DishCategoryDto dishCategoryDto) throws IOException {
        
        DishCategory category = new DishCategory();
        
        if (!Files.exists(Paths.get(uploadPath + SUBDIRECTORY)))
        {
            Files.createDirectory(Paths.get(uploadPath + SUBDIRECTORY));
        }
        MultipartFile pictureFile = dishCategoryDto.getPicture();
        MultipartFile thumbnailFile = dishCategoryDto.getThumbnail();

        Media picture = new Media();
        picture.setPhoto(Paths.get(uploadPath + SUBDIRECTORY + pictureFile.getOriginalFilename()).toString());
        category.setPicture(picture);

        Media thumbnail = new Media();


        thumbnail.setPhoto(Paths.get(uploadPath + SUBDIRECTORY + thumbnailFile.getOriginalFilename()).toString());
        category.setThumbnail(thumbnail);
        category.setProducts(productRepo.findByProductIds(dishCategoryDto.getProducts()));
        return dishCategoryRepo.save(category);

    }

    @Override
    public List<DishCategory> viewDishCategories() {
        return dishCategoryRepo.findAll();
    }
}
