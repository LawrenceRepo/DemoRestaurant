package com.lawrence.web.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Getter@Setter
public class CategoryDto {
    private MultipartFile thumbnail;
    private MultipartFile picture;
    private List<Long> products;
}
