package com.matrix.spring1.dto;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@ApiModel("Model for registration")
public class RegistrationRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
