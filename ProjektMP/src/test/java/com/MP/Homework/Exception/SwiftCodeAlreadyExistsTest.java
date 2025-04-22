package com.MP.Homework.Exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwiftCodeAlreadyExistsTest {

    @Test
    void testMessage() {
        String message = "SWIFT code already exists!";
        SwiftCodeAlreadyExists exception = new SwiftCodeAlreadyExists(message);

        assertEquals(message, exception.getMessage());
    }
}
