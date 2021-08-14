package com.lawrence.web.controller;

import com.lawrence.model.Plant;
import com.lawrence.model.Restaurant;
import com.lawrence.repository.PlantRepo;
import com.lawrence.service.CategoryService;
import com.lawrence.service.PlantService;
import com.lawrence.service.impl.PlantServiceImpl;
import com.lawrence.web.dto.PlantDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
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

@SpringBootTest //This annotation works by creating the ApplicationContext used
// in our tests through SpringApplication. It starts the embedded server,
// creates a web environment and then enables @Test methods to do integration testing.
@AutoConfigureMockMvc
//Enables all auto-configuration related to MockMvc and ONLY MockMvc .
// Again, this is a subset of overall auto-configuration
class PlantControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PlantServiceImpl plantService;
    @Mock
    MultipartFile pic;
    @Test
    void addPlant() throws Exception {
        PlantDto plantDto = new PlantDto();
        plantDto.setName("plant_name");
        plantDto.setCity("London");
        plantDto.setPincode("400098");
        plantDto.setDescription("Describing plant...");
        plantDto.setStreet("21 street");
MultipartFile files[] = new MultipartFile[2];
//        String fileName = "sample-file-mock.txt";
//        MockMultipartFile sampleFile = new MockMultipartFile(
//                "uploaded-file",
//                fileName,
//                "text/plain",
//                "This is the file content".getBytes());
//        MockMultipartHttpServletRequestBuilder multipartRequest =
//                MockMvcRequestBuilders.multipart("/api/files/upload");
        files[0]=pic;
        files[1]=pic;
        plantDto.setFiles(files);

        Plant plant = new Plant();
        plant.setName("plant_name");
        plant.setDescription("Describing plant...");

        when(plantService.addPlant(plantDto)).thenReturn(plant);
        mockMvc.perform(post("/addPlant")
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("/viewPlants"))
                .andExpect(model().attribute("result","Plant added"));
    }



    @Test
    void viewPlants() throws Exception {

        List<Plant> plantList = new ArrayList<Plant>();
        Plant plant = new Plant();
        plant.setId(1);
        plant.setName("plant_name");
        plant.setDescription("Describing plant...");
plantList.add(plant);
        when(plantService.getAllPlants()).thenReturn(plantList);
        mockMvc.perform(get("/viewPlants")
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("/result"))
                .andExpect(model().attribute("result",plantList));
    }
}