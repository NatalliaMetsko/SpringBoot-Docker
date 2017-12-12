package com.netcracker.metsko.exceptions;

public class NotCreatedException extends Throwable {

    private String message;

    public NotCreatedException() {
    }

    public NotCreatedException(String message) {
        this.message = message;
    }


}
