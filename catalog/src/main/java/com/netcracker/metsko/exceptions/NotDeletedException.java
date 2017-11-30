package com.netcracker.metsko.exceptions;

import java.util.Objects;

public class NotDeletedException extends Throwable{

    private String message;

    public NotDeletedException() {
    }

    public NotDeletedException(String message) {
        this.message = message;
    }


}
