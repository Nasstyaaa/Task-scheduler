package org.nastya.backend.exception;

public class TaskAlreadyExistsException extends RuntimeException {
    public TaskAlreadyExistsException() {
        super("A task with that header already exists");
    }
}
