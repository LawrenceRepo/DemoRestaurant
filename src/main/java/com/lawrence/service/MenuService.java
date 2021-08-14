package com.lawrence.service;

import com.lawrence.model.Menu;
import com.lawrence.web.dto.MenuDto;

import java.util.List;

public interface MenuService {
   Menu addMenu(MenuDto menuDto);
    public List<Menu> listMenus();
}
