package com.example.BlogApp.API.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({Exception.class})
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest web){
        ExceptionResponse response = new ExceptionResponse(ex.getMessage(), web.getDescription(false), new Date()) ;
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR) ;
    }

    @ExceptionHandler({UserNotFoundException.class})
    public final ResponseEntity<Object> handleUserNotFoundExceptions(Exception ex, WebRequest web) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), web.getDescription(false), new Date()) ;
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND) ;
    }

}
