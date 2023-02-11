package com.matrix.spring1.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Model for contact us")
public class ContactUsDto {
    private Long id;
    private String phoneNumber;
    private String email;
    private String address;
}
