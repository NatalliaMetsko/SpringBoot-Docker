package com.netcracker.metsko.exceptions;

public class NotFoundException extends Throwable {

    private String message;

    public NotFoundException() {

    }


    public NotFoundException(String message) {

        this.message = message;
    }


}
