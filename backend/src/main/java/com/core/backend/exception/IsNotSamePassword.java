package com.core.backend.exception;

public class IsNotSamePassword extends RuntimeException {

    public IsNotSamePassword() {
        super();
    }

    public IsNotSamePassword(String message) {
        super(message);
    }

    public IsNotSamePassword(String message, Throwable cause) {
        super(message, cause);
    }

    public IsNotSamePassword(Throwable cause) {
        super(cause);
    }
}
