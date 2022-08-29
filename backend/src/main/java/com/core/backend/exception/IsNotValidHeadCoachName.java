package com.core.backend.exception;

public class IsNotValidHeadCoachName extends RuntimeException {
    public IsNotValidHeadCoachName() {
        super();
    }

    public IsNotValidHeadCoachName(String message) {
        super(message);
    }

    public IsNotValidHeadCoachName(String message, Throwable cause) {
        super(message, cause);
    }

    public IsNotValidHeadCoachName(Throwable cause) {
        super(cause);
    }
}
