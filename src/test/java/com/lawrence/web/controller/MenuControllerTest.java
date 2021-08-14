package com.lawrence.web.controller;

import com.lawrence.service.MenuService;
import com.lawrence.web.dto.MenuDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest //This annotation works by creating the ApplicationContext used
// in our tests through SpringApplication. It starts the embedded server,
// creates a web environment and then enables @Test methods to do integration testing.

@AutoConfigureMockMvc//Enables all auto-configuration related to MockMvc and ONLY MockMvc .
// Again, this is a subset of overall auto-configuration
class MenuControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    MenuService menuService;
    @Test
    void addMenu() throws Exception {
        MenuDto menuDto = new MenuDto();
        menuDto.setRestaurant(1);
        menuDto.setWeek(3);
        menuDto.setYear(2021);
        List<Long> sections = Arrays.asList(1L,3L);
        menuDto.setSections(sections);
        menuDto.setWeekday("Saturday");
        menuService.addMenu(menuDto);
            this.mockMvc.perform(post("/addMenu")
                    .contentType(MediaType.TEXT_HTML))
                    .andExpect(status().isOk())
                    .andExpect(view().name("/result"))
                    .andExpect(model().attribute("result","menu added"));

    }

}