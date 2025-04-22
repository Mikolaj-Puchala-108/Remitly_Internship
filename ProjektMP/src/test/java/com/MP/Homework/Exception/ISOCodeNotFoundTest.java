package com.MP.Homework.Exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ISOCodeNotFoundTest {

    @Test
    void testMessage() {
        String errorMessage = "ISO code not found for XX";
        ISOCodeNotFound exception = new ISOCodeNotFound(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }
}