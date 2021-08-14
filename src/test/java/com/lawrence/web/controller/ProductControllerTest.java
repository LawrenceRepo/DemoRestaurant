package com.lawrence.web.controller;

import com.lawrence.model.Dish;
import com.lawrence.model.Product;
import com.lawrence.service.impl.DishServiceImpl;
import com.lawrence.service.impl.ProductServiceImpl;
import com.lawrence.web.dto.DishDto;
import com.lawrence.web.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest //This annotation works by creating the ApplicationContext used
// in our tests through SpringApplication. It starts the embedded server,
// creates a web environment and then enables @Test methods to do integration testing.
@AutoConfigureMockMvc
//Enables all auto-configuration related to MockMvc and ONLY MockMvc .
// Again, this is a subset of overall auto-configuration
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductServiceImpl productService;
    @Mock
    MultipartFile pic;


    @Test
    void addProduct() throws Exception {
ProductDto productDto = new ProductDto();
productDto.setCode("111");
productDto.setName("Product..");
productDto.setDescription("Description ..");

        Product product = new Product();
        product.setCode("111");
        product.setName("Product..");
        product.setDescription("Description ..");

        when(productService.addProduct(productDto)).thenReturn(product);
        mockMvc.perform(post("/addProduct")
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("/result"))
                .andExpect(model().attribute("result","Product added successfully"));
    }
}