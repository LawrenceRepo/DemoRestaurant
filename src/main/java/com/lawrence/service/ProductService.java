package com.lawrence.service;

import com.lawrence.Exception.CommonException;
import com.lawrence.model.Product;
import com.lawrence.web.dto.ProductDto;

import java.util.List;

public interface ProductService {
    public Product addProduct(ProductDto productDto) throws CommonException, Exception;
    List<Product> getAllProducts();
}
