package com.MP.Homework.Exception;

/*
 * Exception thrown when trying to add a SWIFT code that already exists in the database.
 * Triggers an HTTP 409 (Conflict) response.
 */
public class SwiftCodeAlreadyExists extends RuntimeException {

    /*
     * Constructor accepting an error message.
     *
     * @param message description of the exception
     */
    public SwiftCodeAlreadyExists(String message) {
        super(message);
    }
}
