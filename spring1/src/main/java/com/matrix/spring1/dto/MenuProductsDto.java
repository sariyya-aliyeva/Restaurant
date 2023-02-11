package com.matrix.spring1.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Model for menu products")
public class MenuProductsDto {
    private Long id;
    private String productName;
    private String description;
    private int price;
    private Long categoryId;

}
