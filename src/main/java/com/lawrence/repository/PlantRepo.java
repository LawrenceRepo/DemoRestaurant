package com.lawrence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lawrence.model.Plant;

public interface PlantRepo extends JpaRepository<Plant, Long > {
}
