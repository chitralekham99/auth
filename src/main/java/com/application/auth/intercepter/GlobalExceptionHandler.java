package com.application.auth.intercepter;

import com.application.auth.exeption.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>>handleValidationException(MethodArgumentNotValidException exception){

        Map<String,String> errorMap=new HashMap<>();
        List<FieldError>fieldErrorList = exception.getBindingResult().getFieldErrors();

        for(FieldError error:fieldErrorList){
            String key=error.getField();
            String value=error.getDefaultMessage();
            errorMap.put(key,value);
        }
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);

    }
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<Map<String,String>>handleAuthExeption(AuthException authException){
        Map<String,String>errorMap=new HashMap<>();
        errorMap.put("error",authException.getMessage());
        return ResponseEntity.status(authException.getHttpStatus()).body(errorMap);
    }



}
