package com.lawrence.web.controller;

import com.lawrence.service.CategoryService;
import com.lawrence.web.dto.CategoryDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

@SpringBootTest //This annotation works by creating the ApplicationContext used
// in our tests through SpringApplication. It starts the embedded server,
// creates a web environment and then enables @Test methods to do integration testing.

@AutoConfigureMockMvc
//Enables all auto-configuration related to MockMvc and ONLY MockMvc .
// Again, this is a subset of overall auto-configuration
class DishCategoryControllerTest {
@Autowired
    MockMvc mockMvc;
@MockBean
    CategoryService categoryService;
    @Mock
    MultipartFile multipartFile;
    @Test
    void addCategory() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
        CategoryDto categoryDto = new CategoryDto();
        List<Long> products = Arrays.asList(1L,2L,3L);
        categoryDto.setProducts(products);
        categoryDto.setPicture(multipartFile);
        categoryDto.setThumbnail(multipartFile);
        categoryService.addCategory(categoryDto);

    }

    @Test
    void getAllCategories() {
    }
}