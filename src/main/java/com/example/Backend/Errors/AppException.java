package com.example.Backend.Errors;

import lombok.Getter;

@Getter
public class AppException extends Exception{
    private Integer code;
    private String message;

    public AppException(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
