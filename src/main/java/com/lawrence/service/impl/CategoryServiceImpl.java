package com.lawrence.service.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.lawrence.model.Category;
import com.lawrence.model.Media;
import com.lawrence.repository.CategoryRepo;
import com.lawrence.repository.ProductRepo;
import com.lawrence.service.CategoryService;
import com.lawrence.web.dto.CategoryDto;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    ProductRepo productRepo;

    @Value("${file.upload-dir}") // reading from default application properties file
    private String uploadPath;

    @Override
    public void addCategory(CategoryDto categoryDto) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Category category = new Category();

        String subDirectory = "category/";

        if (!Files.exists(Paths.get(uploadPath + subDirectory))) {
            Files.createDirectory(Paths.get(uploadPath + subDirectory));
        }
        
        MultipartFile pictureFile = categoryDto.getPicture();
        MultipartFile thumbnailFile = categoryDto.getThumbnail();
        Files.copy(pictureFile.getInputStream(), Paths.get(uploadPath + subDirectory + pictureFile.getOriginalFilename()));

        Media picture = new Media();
        picture.setPhoto(Paths.get(uploadPath + subDirectory + pictureFile.getOriginalFilename()).toString());
        category.setPicture(picture);

        Media thumbnail = Media.class.getConstructor().newInstance();

        Files.copy(thumbnailFile.getInputStream(), Paths.get(uploadPath + subDirectory + thumbnailFile.getOriginalFilename()));

        thumbnail.setPhoto(Paths.get(uploadPath + subDirectory + thumbnailFile.getOriginalFilename()).toString());
        category.setThumbnail(thumbnail);
        category.setProducts(productRepo.findByProductIds(categoryDto.getProducts()));
        categoryRepo.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }
}
