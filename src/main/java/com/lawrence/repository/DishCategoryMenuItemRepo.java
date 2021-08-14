package com.lawrence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lawrence.model.DishCategoryMenuItem;

public interface DishCategoryMenuItemRepo extends JpaRepository<DishCategoryMenuItem,Long> {
}
