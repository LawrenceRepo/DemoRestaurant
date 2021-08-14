package com.lawrence.service;

import com.lawrence.model.Plant;
import com.lawrence.web.dto.PlantDto;

import java.io.IOException;
import java.util.List;

public interface PlantService {

     Plant addPlant(final PlantDto plant) throws IOException;
     Plant getPlantById(long id);
     public List<Plant> getAllPlants();
}
