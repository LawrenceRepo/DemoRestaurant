package com.lawrence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lawrence.model.DishCategory;
import java.util.List;
import java.util.Set;

public interface DishCategoryRepo extends JpaRepository<DishCategory,Long> {
    Set<DishCategory> findByIdIn(List<Long> ids);
}
