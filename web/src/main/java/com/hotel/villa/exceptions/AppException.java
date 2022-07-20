package com.hotel.villa.exceptions;

public class AppException extends RuntimeException{
    private String msg;
    private String errorCode;

    public AppException(String msg, String errorCode) {
        this.msg = msg;
        this.errorCode = errorCode;
    }
}
