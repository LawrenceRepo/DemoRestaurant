package com.lawrence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lawrence.model.MenuSection;
import java.util.List;

public interface MenuSectionRepo extends JpaRepository<MenuSection,Long> {
    List<MenuSection> findByIdIn(List<Long> ids);
}
