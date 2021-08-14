package com.lawrence.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.lawrence.model.Media;
import com.lawrence.model.MenuSectionTemplate;
import com.lawrence.repository.MenuSectionTemplateRepo;
import com.lawrence.repository.RestaurantRepo;
import com.lawrence.service.MenuSectionTemplateService;
import com.lawrence.web.dto.MenuSectionTemplateDto;

@Service
public class MenuSectionTemplateServiceImpl implements MenuSectionTemplateService {
    @Autowired
    MenuSectionTemplateRepo menuSectionTemplateRepo;
    @Autowired
    RestaurantRepo restaurantRepo;
    @Value("${file.upload-dir}")// reading from default application properties file
    private String uploadPath;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
    @Override
    public void addTemplate(MenuSectionTemplateDto menuSectionTemplateDto) throws IOException {
        MenuSectionTemplate menuSectionTemplate = new MenuSectionTemplate();
        BeanUtils.copyProperties(menuSectionTemplateDto,menuSectionTemplate);
        menuSectionTemplate.setRestaurants(restaurantRepo.findByIdIn(menuSectionTemplateDto.getRestaurants()));
        menuSectionTemplate.setStartTime(LocalDateTime.parse(menuSectionTemplateDto.getStartTime(),dateTimeFormatter));
        menuSectionTemplate.setEndTime(LocalDateTime.parse(menuSectionTemplateDto.getEndTime(),dateTimeFormatter));
        Media thumbnail = new Media();
        Files.copy(menuSectionTemplateDto.getThumbnail().getInputStream(), Paths.get(uploadPath+menuSectionTemplateDto.getThumbnail().getOriginalFilename()));
        thumbnail.setPhoto(uploadPath+menuSectionTemplateDto.getThumbnail().getOriginalFilename());
        Media picture = new Media();
        Files.copy(menuSectionTemplateDto.getPicture().getInputStream(), Paths.get(uploadPath+menuSectionTemplateDto.getPicture().getOriginalFilename()));
        picture.setPhoto(uploadPath+menuSectionTemplateDto.getPicture().getOriginalFilename());
        menuSectionTemplate.setThumbnail(thumbnail);
        menuSectionTemplate.setPicture(picture);
        menuSectionTemplateRepo.save(menuSectionTemplate);
    }

    @Override
    public List<MenuSectionTemplate> viewTemplates() {
        return menuSectionTemplateRepo.findAll();
    }
}
