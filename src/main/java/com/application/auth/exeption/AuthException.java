package com.application.auth.exeption;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data

public class AuthException extends RuntimeException{

    private  final HttpStatus httpStatus;

    AuthException(String massage){

       super(massage);
       this.httpStatus=HttpStatus.BAD_REQUEST;
    }
    public AuthException(String massage, HttpStatus httpStatus){
        super(massage);
        this.httpStatus = httpStatus;
    }


}
