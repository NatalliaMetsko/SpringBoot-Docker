package com.netcracker.metsko.exceptions;

import java.util.Objects;

public class NotCreatedException extends Throwable{

    private String message;

    public NotCreatedException() {
    }

    public NotCreatedException(String message) {
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
        if (!(object instanceof NotCreatedException)) return false;
        NotCreatedException that = (NotCreatedException) object;
        return Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMessage());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NotCreatedException{");
        sb.append("message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
