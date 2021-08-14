package com.lawrence.service.impl;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lawrence.model.Menu;
import com.lawrence.model.WeekDayMenu;
import com.lawrence.repository.MenuRepo;
import com.lawrence.repository.MenuSectionRepo;
import com.lawrence.repository.RestaurantRepo;
import com.lawrence.service.MenuService;
import com.lawrence.web.dto.MenuDto;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuRepo menuRepo;
    @Autowired
    RestaurantRepo restaurantRepo;
    @Autowired
    MenuSectionRepo menuSectionRepo;
    @Override
    public Menu addMenu(MenuDto menuDto) {
        WeekDayMenu menu = new WeekDayMenu();
        BeanUtils.copyProperties(menuDto,menu);
        menu.setRestaurant(restaurantRepo.getById(menuDto.getRestaurant()));
        menu.setSections(menuSectionRepo.findByIdIn(menuDto.getSections()));
        return menuRepo.save(menu);
    }
    @Override
    public List<Menu> listMenus()
    {
     return menuRepo.findAll();
    }
}
