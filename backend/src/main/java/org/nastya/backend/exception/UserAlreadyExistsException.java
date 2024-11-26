package org.nastya.backend.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("A user with that name/email already exists");
    }
}

