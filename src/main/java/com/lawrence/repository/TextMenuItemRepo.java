package com.lawrence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lawrence.model.TextMenuItem;

public interface TextMenuItemRepo extends JpaRepository<TextMenuItem,Long> {
}
