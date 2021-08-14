package com.lawrence.web.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.List;
@Data
public class MenuSectionTemplateDto {
    private int menuRank;
    private String name;
    private String startTime;
    private String endTime;
    @NotEmpty
    private List<Long> restaurants;
    private MultipartFile thumbnail;
    private MultipartFile picture;
}
