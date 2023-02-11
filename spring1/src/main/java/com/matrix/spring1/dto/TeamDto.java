package com.matrix.spring1.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Model for team")
public class TeamDto {
    private Long id;
    private String heading;
    private String text;
    private String memberName;
    private String memberText;
}
