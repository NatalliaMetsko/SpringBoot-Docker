package com.netcracker.metsko.exceptions;

import java.util.Objects;

public class NotFoundException extends Throwable{

    private String message;

    public NotFoundException()
    {

    }


    public NotFoundException(String message)
    {

        this.message=message;
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
        if (!(object instanceof NotFoundException)) return false;
        NotFoundException that = (NotFoundException) object;
        return Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMessage());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NotFoundException{");
        sb.append("message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
