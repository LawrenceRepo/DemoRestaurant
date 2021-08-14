package com.lawrence.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.lawrence.model.DishCategoryMenuItem;
import com.lawrence.model.DishMenuItem;
import com.lawrence.model.Media;
import com.lawrence.model.MenuItem;
import com.lawrence.model.TextMenuItem;
import com.lawrence.repository.DishCategoryRepo;
import com.lawrence.repository.DishRepo;
import com.lawrence.repository.MenuItemRepo;
import com.lawrence.service.MenuItemService;
import com.lawrence.web.dto.DishCategoryMenuItemDto;
import com.lawrence.web.dto.DishMenuItemDto;
import com.lawrence.web.dto.TextMenuItemDto;
@Service
public class MenuItemServiceImpl implements MenuItemService {
    @Autowired
    MenuItemRepo menuItemRepo;
    @Autowired
    DishRepo dishRepo;
    @Autowired
    DishCategoryRepo dishCategoryRepo;
    @Value("${file.upload-dir}")// reading from default application properties file// reading from properties file
    private String uploadPath;
    @Override
    public void addDishMenuItem(DishMenuItemDto dishMenuItemDto) throws IOException {
        DishMenuItem dishMenuItem = new DishMenuItem();
        BeanUtils.copyProperties(dishMenuItemDto,dishMenuItem);
        Media thumbnail = new Media();
        Files.copy(dishMenuItemDto.getThumbnail().getInputStream(), Paths.get(uploadPath+dishMenuItemDto.getThumbnail().getOriginalFilename()));
        thumbnail.setPhoto(uploadPath+dishMenuItemDto.getThumbnail().getOriginalFilename());
        dishMenuItem.setThumbnail(thumbnail);
        dishMenuItem.setDish(dishRepo.getById(dishMenuItemDto.getDish()));
        menuItemRepo.save(dishMenuItem);

    }

    @Override
    public void addTextMenuItem(TextMenuItemDto textMenuItemDto) throws IOException {
        TextMenuItem textMenuItem = new TextMenuItem();
        BeanUtils.copyProperties(textMenuItemDto,textMenuItem);
        Media thumbnail = new Media();
        Files.copy(textMenuItemDto.getThumbnail().getInputStream(), Paths.get(uploadPath+textMenuItemDto.getThumbnail().getOriginalFilename()));
        thumbnail.setPhoto(uploadPath+textMenuItemDto.getThumbnail().getOriginalFilename());
        textMenuItem.setThumbnail(thumbnail);

        menuItemRepo.save(textMenuItem);

    }

    @Override
    public void addDishCategoryMenuItem(DishCategoryMenuItemDto dishCategoryMenuItemDto) throws IOException {
        DishCategoryMenuItem dishCategoryMenuItem = new DishCategoryMenuItem();
        BeanUtils.copyProperties(dishCategoryMenuItemDto,dishCategoryMenuItem);
        Media thumbnail = new Media();
        Files.copy(dishCategoryMenuItemDto.getThumbnail().getInputStream(), Paths.get(uploadPath+dishCategoryMenuItemDto.getThumbnail().getOriginalFilename()));
        thumbnail.setPhoto(uploadPath+dishCategoryMenuItemDto.getThumbnail().getOriginalFilename());
        dishCategoryMenuItem.setThumbnail(thumbnail);
        dishCategoryMenuItem.setDishCategory(dishCategoryRepo.getById(dishCategoryMenuItemDto.getDishCategory()));
        menuItemRepo.save(dishCategoryMenuItem);
    }


    @Override
    public List<MenuItem> viewMenuItems() {
       return menuItemRepo.findAll();
    }


}

//localhost:8090/addMenuItem?section=1&title=breakfast&description=dfsfd&price=89
// form data - thumbnail

//localhost:8090/viewMenuItems

