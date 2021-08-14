package com.lawrence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lawrence.model.Menu;

public interface MenuRepo extends JpaRepository<Menu,Long> {
}
