package com.matrix.spring1.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Model for user")
public class UserDto {
    private String username;
    private String password;
}
