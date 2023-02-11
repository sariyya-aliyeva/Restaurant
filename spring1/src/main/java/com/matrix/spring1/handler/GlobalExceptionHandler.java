package com.matrix.spring1.handler;

import com.matrix.spring1.exception.BadRequestException;
import com.matrix.spring1.exception.ErrorDetails;
import com.matrix.spring1.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

 @ControllerAdvice
public class GlobalExceptionHandler {
     @ExceptionHandler
     public ResponseEntity<ErrorDetails> notFoundData(NotFoundException exception){
         ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), exception.getStatus(), exception.getMessage());
         return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
     }

     @ExceptionHandler
     public ResponseEntity<ErrorDetails> badRequestData(IllegalStateException exception){
         ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(),HttpStatus.BAD_REQUEST, exception.getMessage());
         return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
     }

     @ExceptionHandler
     public ResponseEntity<ErrorDetails> badRequestData(BadRequestException exception){
         ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), exception.getStatus(), exception.getMessage());
         return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
     }

     @ExceptionHandler
     public ResponseEntity<ErrorDetails> x(RuntimeException exception){
         ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), HttpStatus.BAD_REQUEST, exception.getMessage());
         return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
     }
     @ExceptionHandler
     public ResponseEntity<ErrorDetails> y(Exception exception){
         ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), HttpStatus.BAD_GATEWAY, exception.getMessage());
         return new ResponseEntity<>(errorDetails, HttpStatus.BAD_GATEWAY);
     }
}