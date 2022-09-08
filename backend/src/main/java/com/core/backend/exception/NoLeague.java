package com.core.backend.exception;

public class NoLeague extends RuntimeException {

    public NoLeague() {
        super();
    }

    public NoLeague(String message) {
        super(message);
    }

    public NoLeague(String message, Throwable cause) {
        super(message, cause);
    }

    public NoLeague(Throwable cause) {
        super(cause);
    }
}
