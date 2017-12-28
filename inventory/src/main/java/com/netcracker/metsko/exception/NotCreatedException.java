package com.netcracker.metsko.exception;

public class NotCreatedException extends Throwable {

    private String message;

    public NotCreatedException() {
    }

    public NotCreatedException(String message) {
        this.message = message;
    }


}
