package com.core.backend.exception;

public class PasswordNotMatch extends RuntimeException {
    public PasswordNotMatch() {
        super();
    }

    public PasswordNotMatch(String message) {
        super(message);
    }

    public PasswordNotMatch(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordNotMatch(Throwable cause) {
        super(cause);
    }
}
