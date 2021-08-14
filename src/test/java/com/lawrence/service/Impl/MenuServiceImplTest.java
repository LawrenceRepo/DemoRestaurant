package com.lawrence.service.Impl;

import com.lawrence.model.Menu;
import com.lawrence.model.MenuSection;
import com.lawrence.model.Restaurant;
import com.lawrence.repository.MenuRepo;
import com.lawrence.service.MenuService;
import com.lawrence.service.impl.MenuServiceImpl;
import com.lawrence.web.dto.MenuDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
class MenuServiceImplTest {
@InjectMocks
    MenuServiceImpl menuService;
@Mock
    MenuRepo menuRepo;

    @Test
    void addMenu() {
        MenuDto menuDto = new MenuDto();
        menuDto.setRestaurant(1);
        menuDto.setWeek(1);
        menuDto.setWeekday("sunday");
        menuDto.setYear(2021);

        Menu menu = new Menu();
        menu.setId(1);
        menu.setRestaurant(new Restaurant());
        menu.setSections(Arrays.asList(new MenuSection(), new MenuSection()));

        given(menuRepo.save(menu)).willAnswer(invocation->invocation.getArgument(0));
        Menu savedMenu = menuService.addMenu(menuDto);
       verify(menuRepo).save(any(Menu.class));
    }

    @Test
    void listMenus() {
        List<Menu> menuList = new ArrayList<>();

        Menu menu1 = new Menu();
        menu1.setId(1);
        menu1.setRestaurant(new Restaurant());
        menu1.setSections(Arrays.asList(new MenuSection(), new MenuSection()));
        Menu menu2 = new Menu();
        menu2.setId(2);
        menu2.setRestaurant(new Restaurant());
        menu2.setSections(Arrays.asList(new MenuSection(), new MenuSection()));
menuList.add(menu1);
menuList.add(menu2);
given(menuRepo.findAll()).willReturn(menuList);
List<Menu> expectedList = menuService.listMenus();
assertEquals(expectedList,menuList);


    }
}