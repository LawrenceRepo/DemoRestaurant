package com.lawrence.web.controller;

import com.lawrence.service.CategoryService;
import com.lawrence.web.dto.CategoryDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest //This annotation works by creating the ApplicationContext used
// in our tests through SpringApplication. It starts the embedded server,
// creates a web environment and then enables @Test methods to do integration testing.
@AutoConfigureMockMvc//Enables all auto-configuration related to MockMvc and ONLY MockMvc .
// Again, this is a subset of overall auto-configuration
class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CategoryService categoryService;
    @Mock
    MultipartFile pic;

    @Test
    void addCategory() throws Exception {
        CategoryDto categoryDto = new CategoryDto();
        List<Long> products = Arrays.asList(1L,2L,3L);
        categoryDto.setPicture(pic);
        categoryDto.setThumbnail(pic);
        categoryDto.setProducts(products);
        categoryService.addCategory(categoryDto);
        this.mockMvc.perform(post("/addDishCategory")
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("/result"))
                .andExpect(model().attribute("result","category added"));
    }

}