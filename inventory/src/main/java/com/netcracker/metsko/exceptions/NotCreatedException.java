package com.netcracker.metsko.exceptions;

import java.util.Objects;

public class NotCreatedException extends Throwable{

    private String message;

    public NotCreatedException() {
    }

    public NotCreatedException(String message) {
        this.message = message;
    }


}
