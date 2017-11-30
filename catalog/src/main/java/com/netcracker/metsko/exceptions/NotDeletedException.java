package com.netcracker.metsko.exceptions;

import java.util.Objects;

public class NotDeletedException extends Throwable{

    private String message;

    public NotDeletedException() {
    }

    public NotDeletedException(String message) {
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
        if (!(object instanceof NotDeletedException)) return false;
        NotDeletedException that = (NotDeletedException) object;
        return Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMessage());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NotDeletedException{");
        sb.append("message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
