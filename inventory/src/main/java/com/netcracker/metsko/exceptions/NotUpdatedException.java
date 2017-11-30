package com.netcracker.metsko.exceptions;

import java.util.Objects;

public class NotUpdatedException extends Throwable{

    private String message;

    public NotUpdatedException() {
    }

    public NotUpdatedException(String message) {
        this.message = message;
    }

}

