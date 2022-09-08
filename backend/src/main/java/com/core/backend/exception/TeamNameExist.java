package com.core.backend.exception;

public class TeamNameExist extends RuntimeException {

    public TeamNameExist() {
        super();
    }

    public TeamNameExist(String message) {
        super(message);
    }

    public TeamNameExist(String message, Throwable cause) {
        super(message, cause);
    }

    public TeamNameExist(Throwable cause) {
        super(cause);
    }
}
