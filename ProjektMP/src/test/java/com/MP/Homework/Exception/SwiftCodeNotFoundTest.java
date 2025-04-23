package com.MP.Homework.Exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwiftCodeNotFoundTest {

    @Test
    void testMessage() {
        String message = "SWIFT code not found!";
        SwiftCodeNotFound exception = new SwiftCodeNotFound(message);

        assertEquals(message, exception.getMessage());
    }
}