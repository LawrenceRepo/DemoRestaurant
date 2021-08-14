package com.lawrence.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuDto {
//    private List<Integer> sections;
//    private int restaurant;
    private String weekday;
    private int week;
    private int year;
    private List<Long> sections;
    private long restaurant;
}
