package com.matrix.spring1.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Model for menu categories")
public class MenuCategoriesDto {
    private Long id;
    private String categoryName;
}
