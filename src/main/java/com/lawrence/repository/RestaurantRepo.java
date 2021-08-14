package com.lawrence.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.lawrence.model.Restaurant;
import java.util.List;
import java.util.Set;

public interface RestaurantRepo extends JpaRepository<Restaurant, Long > {
    Set<Restaurant> findByIdIn(List<Long> ids);
}
