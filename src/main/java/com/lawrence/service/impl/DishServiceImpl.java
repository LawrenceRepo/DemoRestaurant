package com.lawrence.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.lawrence.model.Dish;
import com.lawrence.model.Media;
import com.lawrence.repository.CategoryRepo;
import com.lawrence.repository.DishRepo;
import com.lawrence.repository.RestaurantRepo;
import com.lawrence.service.DishService;
import com.lawrence.web.dto.DishDto;

@Service
public class DishServiceImpl implements DishService {
    @Autowired
    DishRepo dishRepo;
    @Autowired
    RestaurantRepo restaurantRepo;
    @Autowired
    CategoryRepo categoryRepo;
    @Value("${file.upload-dir}") // reading from default application properties file
    private String uploadPath;
    static final String SUBDIRECTORY = "dish/";

    @Override
    public Dish addDish(DishDto dishDto) throws IOException {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDto, dish);
        if (!Files.exists(Paths.get(uploadPath + SUBDIRECTORY))) {
            Files.createDirectory(Paths.get(uploadPath + SUBDIRECTORY));
        }
        dish.setRestaurants(restaurantRepo.findByIdIn(dishDto.getRestaurants()));
        MultipartFile pictureFile = dishDto.getPicture();
        MultipartFile thumbnailFile = dishDto.getThumbnail();
        Files.copy(pictureFile.getInputStream(), Paths.get(uploadPath + SUBDIRECTORY + pictureFile.getOriginalFilename()));

        Media picture = new Media();
        picture.setPhoto(Paths.get(uploadPath + SUBDIRECTORY + pictureFile.getOriginalFilename()).toString());
        dish.setPicture(picture);

        Media thumbnail = new Media();

        Files.copy(thumbnailFile.getInputStream(), Paths.get(uploadPath + SUBDIRECTORY + thumbnailFile.getOriginalFilename()));

        thumbnail.setPhoto(Paths.get(uploadPath + SUBDIRECTORY + thumbnailFile.getOriginalFilename()).toString());
        dish.setThumbnail(thumbnail);
        dish.setSupercategories(categoryRepo.findByCategoryIds(dishDto.getSupercategories()));
        return dishRepo.save(dish);
    }

    @Override
    public List<Dish> allDishes() {

        return dishRepo.findAll();
    }
}


