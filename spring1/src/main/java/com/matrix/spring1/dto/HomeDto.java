package com.matrix.spring1.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Model for home")
public class HomeDto {
    private Long id;
    private String heading;
    private String text1;
    private String text2;

}
