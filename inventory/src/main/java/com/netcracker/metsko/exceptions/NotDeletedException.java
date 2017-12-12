package com.netcracker.metsko.exceptions;

public class NotDeletedException extends Throwable {

    private String message;

    public NotDeletedException() {
    }

    public NotDeletedException(String message) {
        this.message = message;
    }


}
