package com.lawrence.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.lawrence.model.DishCategoryToRestaurant;
import com.lawrence.model.Media;
import com.lawrence.repository.DishCategoryToRestaurantRepo;
import com.lawrence.repository.ProductRepo;
import com.lawrence.repository.RestaurantRepo;
import com.lawrence.service.DishCategoryToRestaurantService;
import com.lawrence.web.dto.DishCategoryToRestaurantDto;

@Service
public class DishCategoryToRestaurantServiceImpl implements DishCategoryToRestaurantService {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    DishCategoryToRestaurantRepo dishCategoryToRestaurantRepo;
    @Autowired
    RestaurantRepo restaurantRepo;
    @Value("${file.upload-dir}") // reading from default application properties file
    private String uploadPath;
    static final String SUBDIRECTORY = "category/";

    @Override
    public void addDishCategory(DishCategoryToRestaurantDto dishCategoryToRestaurantDto) throws IOException {

        DishCategoryToRestaurant category = new DishCategoryToRestaurant();

        if (!Files.exists(Paths.get(uploadPath + SUBDIRECTORY))) {

            Files.createDirectory(Paths.get(uploadPath + SUBDIRECTORY));
        }

        MultipartFile pictureFile = dishCategoryToRestaurantDto.getPicture();
        MultipartFile thumbnailFile = dishCategoryToRestaurantDto.getThumbnail();

        Files.copy(pictureFile.getInputStream(), Paths.get(uploadPath + SUBDIRECTORY + pictureFile.getOriginalFilename()));

        Media picture = new Media();
        picture.setPhoto(Paths.get(uploadPath + SUBDIRECTORY + pictureFile.getOriginalFilename()).toString());
        category.setPicture(picture);

        Media thumbnail = new Media();

        Files.copy(thumbnailFile.getInputStream(), Paths.get(uploadPath + SUBDIRECTORY + thumbnailFile.getOriginalFilename()));

        thumbnail.setPhoto(Paths.get(uploadPath + SUBDIRECTORY + thumbnailFile.getOriginalFilename()).toString());
        category.setThumbnail(thumbnail);
        category.setProducts(productRepo.findByProductIds(dishCategoryToRestaurantDto.getProducts()));
        category.setRestaurants(restaurantRepo.findByIdIn(dishCategoryToRestaurantDto.getRestaurants()));
        dishCategoryToRestaurantRepo.save(category);

    }

    @Override
    public List<DishCategoryToRestaurant> viewDishCategoriesToRestaurant() {

        return dishCategoryToRestaurantRepo.findAll();
    }

}
