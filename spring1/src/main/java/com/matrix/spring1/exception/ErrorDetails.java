package com.matrix.spring1.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    private LocalDate exceptionDate;
    private HttpStatus status;
    private  String message;
}
