package com.matrix.spring1.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Model for blog")
public class BlogDto {
    private Long id;
    private String heading;
    private String date;
    private String text;
    private String author;
}
