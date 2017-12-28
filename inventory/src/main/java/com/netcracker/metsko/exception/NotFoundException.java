package com.netcracker.metsko.exception;

public class NotFoundException extends Throwable {

    private String message;

    public NotFoundException() {

    }


    public NotFoundException(String message) {

        this.message = message;
    }


}
