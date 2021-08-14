package com.lawrence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lawrence.model.DishMenuItem;

public interface DishMenuItemRepo extends JpaRepository<DishMenuItem,Long> {
}
