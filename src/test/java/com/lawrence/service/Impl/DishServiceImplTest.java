package com.lawrence.service.Impl;

import com.lawrence.model.Dish;
import com.lawrence.model.DishCategory;
import com.lawrence.repository.DishRepo;
import com.lawrence.service.impl.DishServiceImpl;
import com.lawrence.web.dto.DishDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
class DishServiceImplTest {

    @InjectMocks
    DishServiceImpl dishService;
    @Mock
    DishRepo dishRepo;


    @Test
    void addDish() throws IOException {
        DishDto dishDto = new DishDto();
        dishDto.setCode("111");
        dishDto.setName("dish 1");
        dishDto.setDescription("describing dish");

        Dish dish = new Dish();
        dish.setId(1L);
        dish.setName("dish 1");
        dish.setDescription("describing dish");

        given(dishRepo.save(dish)).willAnswer(invocation->invocation.getArgument(0));
         Dish savedDish = dishService.addDish(dishDto);
        verify( dishRepo).save(any(Dish.class));
    }

    @Test
    void allDishes() {
    }
}