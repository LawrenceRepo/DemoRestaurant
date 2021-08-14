package com.lawrence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lawrence.model.MenuItem;

public interface MenuItemRepo extends JpaRepository<MenuItem,Long> {
}
