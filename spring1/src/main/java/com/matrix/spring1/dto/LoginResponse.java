package com.matrix.spring1.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Model for login")
public class LoginResponse {
    String username;
    String firstName;
    String lastName;
    String token;
}
