package com.lawrence.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OpeningScheduleDto {
    private List<Long> openingDays;
}
