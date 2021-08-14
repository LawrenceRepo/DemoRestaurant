package com.lawrence.service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import com.lawrence.model.DishCategory;
import com.lawrence.model.Product;
import com.lawrence.repository.DishCategoryRepo;
import com.lawrence.repository.ProductRepo;
import com.lawrence.service.impl.DishCategoryServiceImpl;
import com.lawrence.web.dto.DishCategoryDto;
@SpringBootTest
class DishCategoryServiceImplTest {
    @InjectMocks
    DishCategoryServiceImpl dishCategoryService;

    @Mock
    DishCategoryRepo dishCategoryRepo;
    @Mock
    ProductRepo productRepo;
    @Mock
    MultipartFile file;

    @TempDir
     Path uploadPath;
    @BeforeEach
    public void init()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addDishCategory() throws IOException {
        FileSystem fileSystem = Jimfs.newFileSystem(Configuration.windows());
        DishCategoryDto dishCategoryDto = new DishCategoryDto();
        List<Long> products = Arrays.asList(1L,2L);
        dishCategoryDto.setProducts(products);
        dishCategoryDto.setPicture(file);
        dishCategoryDto.setThumbnail(file);

       DishCategory dishCategory = new DishCategory();
       dishCategory.setId(1L);
       dishCategory.setProducts(productRepo.findByProductIds(products));
  given(dishCategoryRepo.save(dishCategory)).willAnswer(invocation->invocation.getArgument(0));
        DishCategory savedDishCategory =dishCategoryService.addDishCategory(dishCategoryDto);
//        assertThat(savedDishCategory).isNotNull();
//        when(userService.checkIfUserExist("sa@gmail.com")).thenReturn(true);

        verify( dishCategoryRepo).save(any(DishCategory.class));
    }

    @Test
    void viewDishCategories() {
        List<DishCategory> dishCategoryList = new ArrayList<>();
        DishCategory dishCategory1 = new DishCategory();
        dishCategory1.setId(1L);
        dishCategory1.setProducts(Stream.of(new Product(),new Product()).collect(Collectors.toSet()));
        DishCategory dishCategory2 = new DishCategory();
        dishCategory2.setId(1L);
        dishCategory2.setProducts(Stream.of(new Product(),new Product()).collect(Collectors.toSet()));
        dishCategoryList.add(dishCategory1);
        dishCategoryList.add(dishCategory2);

        given(dishCategoryRepo.findAll()).willReturn(dishCategoryList);
        List<DishCategory> expected = dishCategoryService.viewDishCategories();
        assertEquals(expected,dishCategoryList);
    }
}