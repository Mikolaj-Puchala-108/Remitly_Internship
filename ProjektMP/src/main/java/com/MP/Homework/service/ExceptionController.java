package com.MP.Homework.service;

import com.MP.Homework.Exception.ISOCodeNotFound;
import com.MP.Homework.Exception.SwiftCodeAlreadyExists;
import com.MP.Homework.Exception.SwiftCodeNotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

/*
 * ExceptionController intercepts and handles exceptions throughout the REST application.
 * With the @RestControllerAdvice annotation, it works globally for all controllers.
 * Each type of error is mapped to the appropriate HTTP status code and a JSON-formatted message.
 */
@RestControllerAdvice
public class ExceptionController {


    /*
     * Handles input validation errors (e.g., from @Valid).
     * Returns a map with field names and corresponding error messages.
     *
     * @param ex validation exception
     * @return HTTP 400 response with error map
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(errors);
    }

    /*
     * Handles SwiftCodeNotFound exception.
     * Returns status 404 with an error message.
     *
     * @param ex exception indicating missing SWIFT code
     * @return HTTP 404 response with message
     */
    @ExceptionHandler(SwiftCodeNotFound.class)
    public ResponseEntity<Map<String, String>> handleSwiftCodeNotFound(SwiftCodeNotFound ex) {
        return buildErrorResponse(ex.getMessage(), 404);
    }

    /*
     * Handles ISOCodeNotFound exception.
     * Returns status 404 with an error message.
     *
     * @param ex exception indicating missing data for ISO code
     * @return HTTP 404 response with message
     */
    @ExceptionHandler(ISOCodeNotFound.class)
    public ResponseEntity<Map<String, String>> handleISOCodeNotFound(ISOCodeNotFound ex) {
        return buildErrorResponse(ex.getMessage(), 404);
    }

    /*
     * Handles SwiftCodeAlreadyExists exception.
     * Returns status 409 if a duplicate SWIFT code is being added.
     *
     * @param ex exception indicating conflict (duplicate)
     * @return HTTP 409 response with message
     */
    @ExceptionHandler(SwiftCodeAlreadyExists.class)
    public ResponseEntity<Map<String, String>> handleSwiftCodeAlreadyExists(SwiftCodeAlreadyExists ex) {
        return buildErrorResponse(ex.getMessage(), 409);
    }


    /*
     * Helper method for creating a standardized error response.
     *
     * @param message error message
     * @param statusCode HTTP status code, e.g., 404 or 409
     * @return ResponseEntity with a map {"message": "..."}
     */
    private ResponseEntity<Map<String, String>> buildErrorResponse(String message, int statusCode) {
        Map<String, String> error = new HashMap<>();
        error.put("message", message);
        return ResponseEntity.status(statusCode).body(error);
    }
}
