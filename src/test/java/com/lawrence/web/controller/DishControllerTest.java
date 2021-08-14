package com.lawrence.web.controller;

import com.lawrence.model.Dish;
import com.lawrence.model.Plant;
import com.lawrence.service.impl.DishServiceImpl;
import com.lawrence.service.impl.PlantServiceImpl;
import com.lawrence.web.dto.DishDto;
import com.lawrence.web.dto.PlantDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@SpringBootTest //This annotation works by creating the ApplicationContext used
// in our tests through SpringApplication. It starts the embedded server,
// creates a web environment and then enables @Test methods to do integration testing.
@AutoConfigureMockMvc
//Enables all auto-configuration related to MockMvc and ONLY MockMvc .
// Again, this is a subset of overall auto-configuration
class DishControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DishServiceImpl dishService;
    @Mock
    MultipartFile pic;



    @Test
    void addDish() throws Exception {
        DishDto dishDto = new DishDto();
        dishDto.setCode("111");
        dishDto.setDescription("Describing dish..");
       dishDto.setEmployeePrice(55.55);
       dishDto.setThumbnail(pic);
        dishDto.setPicture(pic);
        Dish dish = new Dish();
        dish.setCode("111");
        dish.setDescription("Describing dish..");
        dish.setEmployeePrice(55.55);



        when(dishService.addDish(dishDto)).thenReturn(dish);
        mockMvc.perform(post("/addDish")
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("/result"))
                .andExpect(model().attribute("result","dish added"));
    }

    @Test
    void allDishes() throws Exception {
List<Dish> dishList = new ArrayList<>();
        Dish dish = new Dish();
        dish.setCode("111");
        dish.setDescription("Describing dish..");
        dish.setEmployeePrice(55.55);
        dishList.add(dish);
        when(dishService.allDishes()).thenReturn(dishList);
        mockMvc.perform(get("/alldishes")
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("/result"))
                .andExpect(model().attribute("result",dishList));

    }


}