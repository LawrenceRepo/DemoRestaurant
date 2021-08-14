package com.lawrence.service;

import com.lawrence.model.MenuItem;
import com.lawrence.web.dto.DishCategoryMenuItemDto;
import com.lawrence.web.dto.DishMenuItemDto;
import com.lawrence.web.dto.TextMenuItemDto;

import java.io.IOException;
import java.util.List;


public interface MenuItemService {

     void addDishMenuItem(DishMenuItemDto dishMenuItemDto) throws IOException;
//     List<DishMenuItem> viewDishMenuItems();
     void addTextMenuItem(TextMenuItemDto textMenuItemDto) throws IOException;
//     List<TextMenuItem> viewTextMenuItems();
     void addDishCategoryMenuItem(DishCategoryMenuItemDto textMenuItemDto) throws IOException;
//     List<DishCategoryMenuItem> viewDishcategoryMenuItems();
     List<MenuItem> viewMenuItems();
}

