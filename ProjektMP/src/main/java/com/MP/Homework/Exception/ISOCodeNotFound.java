package com.MP.Homework.Exception;

/*
 * Exception thrown when no SWIFT data is found for the provided country ISO2 code.
 *
 * Used in the service layer to inform the controller about the lack of data for the given country.
 */
public class ISOCodeNotFound extends RuntimeException {


    /*
     * Creates a new exception with the provided message.
     *
     * @param message error message, e.g., "No SWIFT codes found for country ISO2"
     */
    public ISOCodeNotFound(String message) {
        super(message);
    }
}