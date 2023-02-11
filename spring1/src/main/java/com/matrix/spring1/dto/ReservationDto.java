package com.matrix.spring1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@ApiModel("Model for reservation")
public class ReservationDto {
    private Long id;
    private String name;
    private String contactNo;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate date;
    private Integer noOfPeople;
    private String preferredFood;
    private String occasion;
}
