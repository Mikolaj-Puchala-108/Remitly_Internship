package com.MP.Homework.Exception;

/*
 * Wyjątek rzucany, gdy próbuje się dodać kod SWIFT, który już istnieje w bazie danych.
 * Powoduje zwrócenie odpowiedzi HTTP 409 (Conflict).
 */
public class SwiftCodeAlreadyExists extends RuntimeException {

    /*
     * Konstruktor przyjmujący wiadomość błędu.
     *
     * @param message opis wyjątku
     */
    public SwiftCodeAlreadyExists(String message) {
        super(message);
    }
}
