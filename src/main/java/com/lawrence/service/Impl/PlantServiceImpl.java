package com.lawrence.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.lawrence.model.Address;
import com.lawrence.model.Media;
import com.lawrence.model.Plant;
import com.lawrence.repository.OpeningScheduleRepo;
import com.lawrence.repository.PlantRepo;
import com.lawrence.service.PlantService;
import com.lawrence.web.dto.PlantDto;

@Service
public class PlantServiceImpl implements PlantService {

    @Autowired
    PlantRepo plantRepo;
    @Value("${file.upload-dir}") // reading from default application properties file
    private String uploadPath;
    @Autowired
    OpeningScheduleRepo openingScheduleRepo;
    static final String SUBDIRECTORY = "plant/";

    @Override
    @Transactional
    public Plant addPlant(PlantDto plantdto) throws IOException {
        Plant plant = new Plant();
        if (!Files.exists(Paths.get(uploadPath + SUBDIRECTORY)))
            Files.createDirectory(Paths.get(uploadPath + SUBDIRECTORY));
        Address address = new Address();
        address.setCity(plantdto.getCity());
        address.setStreet(plantdto.getStreet());
        address.setState(plantdto.getState());
        address.setPincode(plantdto.getPincode());
        plant.setAddress(address);
        plant.setDescription(plantdto.getDescription());
        plant.setName(plantdto.getName());
        plant.setOpeningSchedule(openingScheduleRepo.getById(plantdto.getOpeningSchedule()));

        List<Media> mediaList = new ArrayList<>();

        for (MultipartFile file : plantdto.getFiles()) {
            Files.copy(file.getInputStream(), Paths.get(uploadPath + SUBDIRECTORY + file.getOriginalFilename()));
            Media media = new Media();
            media.setPhoto(Paths.get(uploadPath + SUBDIRECTORY + file.getOriginalFilename()).toString());
            mediaList.add(media);
        }
        plant.setMedia(mediaList);
        return plantRepo.save(plant);
    }

    @Override
    public Plant getPlantById(long id) {
        return plantRepo.getById(id);
    }

    public List<Plant> getAllPlants() {
        return plantRepo.findAll();
    }
}
