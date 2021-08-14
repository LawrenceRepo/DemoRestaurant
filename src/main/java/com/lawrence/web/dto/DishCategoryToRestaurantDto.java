package com.lawrence.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DishCategoryToRestaurantDto extends DishCategoryDto{
    private boolean thumbnailVisible;
    private List<Long> restaurants;
}
