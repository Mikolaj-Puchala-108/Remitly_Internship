package com.MP.Homework.Exception;

/*
 * Wyjątek rzucany, gdy nie znaleziono żadnych danych SWIFT powiązanych z podanym kodem ISO2 kraju.
 *
 * Jest używany w warstwie serwisu, aby poinformować kontroler o braku danych dla danego kraju.
 */
public class ISOCodeNotFound extends RuntimeException {

    /*
     * Tworzy nowy wyjątek z podaną wiadomością.
     *
     * @param message komunikat błędu, np. "No SWIFT codes found for country ISO2"
     */
    public ISOCodeNotFound(String message) {
        super(message);
    }
}