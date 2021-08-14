package com.lawrence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lawrence.model.DishCategoryToRestaurant;
import java.util.List;
import java.util.Set;

public interface DishCategoryToRestaurantRepo extends JpaRepository<DishCategoryToRestaurant,Long> {
Set<DishCategoryToRestaurant> findByIdIn(List<Long> ids);
}
