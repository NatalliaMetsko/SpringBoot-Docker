package com.netcracker.metsko.exception;

public class NotDeletedException extends Throwable {

    private String message;

    public NotDeletedException() {
    }

    public NotDeletedException(String message) {
        this.message = message;
    }


}
