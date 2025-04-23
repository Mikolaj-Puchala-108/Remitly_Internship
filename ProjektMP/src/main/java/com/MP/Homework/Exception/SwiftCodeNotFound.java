package com.MP.Homework.Exception;

/*
 * Exception thrown when the requested SWIFT code is not found in the database.
 * Triggers an HTTP 404 (Not Found) response.
 */
public class SwiftCodeNotFound extends RuntimeException {

    /*
     * Constructor accepting an error message.
     *
     * @param message description of the exception
     */
    public SwiftCodeNotFound(String message) {
        super(message);
    }
}