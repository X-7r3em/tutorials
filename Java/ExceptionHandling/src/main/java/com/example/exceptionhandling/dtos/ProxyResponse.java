package com.example.exceptionhandling.dtos;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;

/**
 * Needed to create an entity that conains both error information and the entity information.
 * This way I can receive both Error information and object information in one entity.
 */
public class ProxyResponse {
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ProxyResponse() {
    }

    public ProxyResponse(HttpStatus status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProxyResponse that = (ProxyResponse) o;
        return status == that.status &&
                Objects.equals(message, that.message) &&
                Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message, errors);
    }

    @Override
    public String toString() {
        return "ProxyResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", errors=" + errors +
                '}';
    }
}
