package com.lawrence.service.Impl;

import com.lawrence.model.DishCategoryToRestaurant;
import com.lawrence.model.Media;
import com.lawrence.model.Restaurant;
import com.lawrence.repository.*;
import com.lawrence.service.PlantService;
import com.lawrence.service.impl.PlantServiceImpl;
import com.lawrence.service.impl.RestaurantServiceImpl;
import com.lawrence.web.dto.RestaurantDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class RestaurantServiceImplTest {
    @InjectMocks
    RestaurantServiceImpl restaurantService;
    @Mock
    RestaurantRepo restaurantRepo;
    @Mock
    OpeningScheduleRepo openingScheduleRepo;
    @Mock
    PlantServiceImpl plantService;
    @Mock
    MultipartFile pic;
    @Mock
    MenuRepo menuRepo;
    @Mock
    DishCategoryToRestaurantRepo dishCategoryToRestaurantRepo;
    @Mock
    DishRepo dishRepo;
    @Value("${file.upload-dir}")// reading from default application properties file
    private String uploadPath;
    @BeforeEach
    public void init()
    {
        MockitoAnnotations.initMocks(this);


    }


    @Test
    void listAllRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant1 = new Restaurant();
        restaurant1.setName("Res 1");

        restaurants.add(restaurant1);
        Restaurant restaurant2 = new Restaurant();
        restaurant2.setName("Res 2");
        restaurants.add(restaurant2);
        when(restaurantService.listAllRestaurants()).thenReturn(restaurants);
        List<Restaurant> restaurantList = restaurantService.listAllRestaurants();
        assertEquals(2, restaurantList.size());
    }
}