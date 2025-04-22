package com.MP.Homework.Exception;

/*
 * Wyjątek rzucany, gdy żądany kod SWIFT nie zostanie znaleziony w bazie danych.
 * Powoduje zwrócenie odpowiedzi HTTP 404 (Not Found).
 */
public class SwiftCodeNotFound extends RuntimeException {

    /*
     * Konstruktor przyjmujący wiadomość błędu.
     *
     * @param message opis wyjątku
     */
    public SwiftCodeNotFound(String message) {
        super(message);
    }
}