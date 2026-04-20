package com.experiment.studentapi.exception;

/**
 * Thrown when a student with the given email already exists.
 */
public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super("A student with email '" + email + "' already exists");
    }
}