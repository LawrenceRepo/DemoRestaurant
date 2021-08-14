package com.lawrence.service;


import com.lawrence.model.Category;
import com.lawrence.web.dto.CategoryDto;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CategoryService {
    public void addCategory(CategoryDto categoryDto) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
    List<Category> getAllCategories();
}
