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
 * ExceptionController przechwytuje i obsługuje wyjątki w całej aplikacji REST.
 * Dzięki adnotacji @RestControllerAdvice działa globalnie dla wszystkich kontrolerów.
 * Każdy typ błędu mapowany jest na odpowiedni kod HTTP oraz wiadomość w formacie JSON.
 */
@RestControllerAdvice
public class ExceptionController {


    /*
     * Obsługa błędów walidacji danych wejściowych (np. @Valid).
     * Zwraca mapę z nazwą pola i komunikatem błędu.
     *
     * @param ex wyjątek walidacji
     * @return odpowiedź 400 z mapą błędów
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
     * Obsługa wyjątku SwiftCodeNotFound.
     * Zwraca status 404 z komunikatem.
     *
     * @param ex wyjątek informujący o braku kodu SWIFT
     * @return odpowiedź 404 z wiadomością
     */
    @ExceptionHandler(SwiftCodeNotFound.class)
    public ResponseEntity<Map<String, String>> handleSwiftCodeNotFound(SwiftCodeNotFound ex) {
        return buildErrorResponse(ex.getMessage(), 404);
    }

    /*
     * Obsługa wyjątku ISOCodeNotFound.
     * Zwraca status 404 z komunikatem.
     *
     * @param ex wyjątek informujący o braku danych dla kodu ISO
     * @return odpowiedź 404 z wiadomością
     */
    @ExceptionHandler(ISOCodeNotFound.class)
    public ResponseEntity<Map<String, String>> handleISOCodeNotFound(ISOCodeNotFound ex) {
        return buildErrorResponse(ex.getMessage(), 404);
    }

    /*
     * Obsługa wyjątku SwiftCodeAlreadyExists.
     * Zwraca status 409, jeśli próbujemy dodać już istniejący kod.
     *
     * @param ex wyjątek informujący o konflikcie (duplikacie)
     * @return odpowiedź 409 z wiadomością
     */
    @ExceptionHandler(SwiftCodeAlreadyExists.class)
    public ResponseEntity<Map<String, String>> handleSwiftCodeAlreadyExists(SwiftCodeAlreadyExists ex) {
        return buildErrorResponse(ex.getMessage(), 409);
    }


    /*
     * Pomocnicza metoda do tworzenia ustandaryzowanej odpowiedzi błędu.
     *
     * @param message komunikat błędu
     * @param statusCode kod HTTP, np. 404 lub 409
     * @return ResponseEntity z mapą {"message": "..."}
     */
    private ResponseEntity<Map<String, String>> buildErrorResponse(String message, int statusCode) {
        Map<String, String> error = new HashMap<>();
        error.put("message", message);
        return ResponseEntity.status(statusCode).body(error);
    }
}
