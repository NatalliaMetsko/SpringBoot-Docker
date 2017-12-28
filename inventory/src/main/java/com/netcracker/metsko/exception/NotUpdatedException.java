package com.netcracker.metsko.exception;

public class NotUpdatedException extends Throwable {

    private String message;

    public NotUpdatedException() {
    }

    public NotUpdatedException(String message) {
        this.message = message;
    }

}

