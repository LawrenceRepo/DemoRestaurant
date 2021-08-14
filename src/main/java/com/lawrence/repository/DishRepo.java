package com.lawrence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lawrence.model.Dish;
import java.util.List;
import java.util.Set;

public interface DishRepo extends JpaRepository<Dish,Long> {
    Set<Dish> findByIdIn(List<Long> ids);
}
