package com.example.Backend.statusCode;

import lombok.Getter;

@Getter
public class StatusCode {
    private int code;
    private String status;

    public StatusCode(int code, String status){
        this.code = code;
        this.status = status;
    }
}
