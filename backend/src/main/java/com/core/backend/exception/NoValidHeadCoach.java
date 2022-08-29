package com.core.backend.exception;

public class NoValidHeadCoach extends RuntimeException {
    public NoValidHeadCoach() {
        super();
    }

    public NoValidHeadCoach(String message) {
        super(message);
    }

    public NoValidHeadCoach(String message, Throwable cause) {
        super(message, cause);
    }

    public NoValidHeadCoach(Throwable cause) {
        super(cause);
    }
}
