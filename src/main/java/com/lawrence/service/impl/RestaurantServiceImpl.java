package com.lawrence.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.lawrence.model.Address;
import com.lawrence.model.Media;
import com.lawrence.model.Restaurant;
import com.lawrence.repository.DishCategoryToRestaurantRepo;
import com.lawrence.repository.DishRepo;
import com.lawrence.repository.OpeningScheduleRepo;
import com.lawrence.repository.RestaurantRepo;
import com.lawrence.service.PlantService;
import com.lawrence.service.RestaurantService;
import com.lawrence.web.dto.RestaurantDto;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    RestaurantRepo restaurantRepo;

    @Autowired
    OpeningScheduleRepo openingScheduleRepo;

    @Autowired
    PlantService plantService;

    @Autowired
    DishRepo dishRepo;

    @Autowired
    DishCategoryToRestaurantRepo dishCategoryToRestaurantRepo;

    @Value("${file.upload-dir}") // reading from default application properties file
    private String uploadPath;

    @Override
    public Restaurant add(RestaurantDto restaurantdto) throws IOException {
        Restaurant restaurant = new Restaurant();
        if (!Files.exists(Paths.get(uploadPath + "restaurant/")))
            Files.createDirectory(Paths.get(uploadPath + "restaurant/"));
        Address address = new Address();
        address.setCity(restaurantdto.getCity());
        address.setStreet(restaurantdto.getStreet());
        address.setState(restaurantdto.getState());
        address.setPincode(restaurantdto.getPincode());
        restaurant.setAddress(address);
        restaurant.setServesGuest(restaurantdto.isServesGuest());
        restaurant.setName(restaurantdto.getName());
        restaurant.setDescription(restaurantdto.getDescription());
        restaurant.setPlant(plantService.getPlantById(restaurantdto.getPlant()));
        restaurant.setServesGuest(restaurantdto.isServesGuest());

        restaurant.setOpeningSchedule(openingScheduleRepo.getById(restaurantdto.getOpeningSchedule()));

        if (restaurantdto.getDishes() != null)
            restaurant.setDishes(dishRepo.findByIdIn(restaurantdto.getDishes()));
        if (restaurantdto.getDishCategories() != null)
            restaurant.setDishCategories(dishCategoryToRestaurantRepo.findByIdIn(restaurantdto.getDishCategories()));

        List<Media> mediaList = new ArrayList<>();
        for (MultipartFile file : restaurantdto.getFiles()) {
            Files.copy(file.getInputStream(), Paths.get(uploadPath + "restaurant/" + file.getOriginalFilename()));
            Media media = new Media();
            media.setPhoto(Paths.get(uploadPath + "restaurant/" + file.getOriginalFilename()).toString());
            mediaList.add(media);
        }
        restaurant.setMedia(mediaList);
        return restaurantRepo.save(restaurant);
    }

    @Override
    public List<Restaurant> listAllRestaurants() {
        return restaurantRepo.findAll();
    }

}
