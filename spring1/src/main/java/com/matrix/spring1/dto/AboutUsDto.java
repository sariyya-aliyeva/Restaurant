package com.matrix.spring1.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Model for information about us")
public class AboutUsDto {
    private Long id;
    @ApiModelProperty("heading of about us")
    private String heading;
    private String text1;
    private String text2;
    private String text3;
    private String text4;

}
