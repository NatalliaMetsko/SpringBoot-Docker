package com.netcracker.metsko.exceptions;

import java.util.Objects;

public class NotUpdatedException extends Throwable{

    private String message;

    public NotUpdatedException() {
    }

    public NotUpdatedException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof NotUpdatedException)) return false;
        NotUpdatedException that = (NotUpdatedException) object;
        return Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMessage());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NotUpdatedException{");
        sb.append("message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

