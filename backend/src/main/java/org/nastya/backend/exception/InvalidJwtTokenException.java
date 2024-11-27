package org.nastya.backend.exception;

public class InvalidJwtTokenException extends RuntimeException {
    public InvalidJwtTokenException() {
        super("The login has expired, please log in again");
    }
}
