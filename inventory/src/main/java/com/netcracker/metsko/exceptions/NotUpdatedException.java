package com.netcracker.metsko.exceptions;

public class NotUpdatedException extends Throwable {

    private String message;

    public NotUpdatedException() {
    }

    public NotUpdatedException(String message) {
        this.message = message;
    }

}

