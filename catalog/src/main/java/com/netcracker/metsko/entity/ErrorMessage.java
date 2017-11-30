package com.netcracker.metsko.entity;

import java.util.Objects;

public class ErrorMessage {

    private Integer status;

    private String errorMessage;


    public ErrorMessage() {
    }

    public ErrorMessage(Integer status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof ErrorMessage)) return false;
        ErrorMessage that = (ErrorMessage) object;
        return Objects.equals(getStatus(), that.getStatus()) &&
                Objects.equals(getErrorMessage(), that.getErrorMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatus(), getErrorMessage());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErrorMessage{");
        sb.append("status=").append(status);
        sb.append(", errorMessage='").append(errorMessage).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

