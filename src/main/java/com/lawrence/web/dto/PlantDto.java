package com.lawrence.web.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter@Setter
public class PlantDto {
    private String name;
    private String street;
    private String city;
    private String state;
    private String pincode;
    private String description;
    private MultipartFile[] files;
    private Long openingSchedule;
}
