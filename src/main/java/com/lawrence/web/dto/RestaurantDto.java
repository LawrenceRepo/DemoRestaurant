package com.lawrence.web.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Getter@Setter
public class RestaurantDto {
    private boolean servesGuest;
    private int plant;
    private String name;
    private String street;
    private String city;
    private String state;
    private String pincode;
    private MultipartFile[] files;
    private Long openingSchedule;
    private String description;
    private List<Long> dishes;
    private List<Long> menus;
    private List<Long> dishCategories;
}
