package com.MP.Homework.service;

import com.MP.Homework.Exception.ISOCodeNotFound;
import com.MP.Homework.Exception.SwiftCodeAlreadyExists;
import com.MP.Homework.Exception.SwiftCodeNotFound;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ExceptionControllerUnitTest {

    private final ExceptionController controller = new ExceptionController();

    @Test
    void handleSwiftCodeNotFound() {
        SwiftCodeNotFound ex = new SwiftCodeNotFound("SWIFT code not found");
        ResponseEntity<Map<String, String>> response = controller.handleSwiftCodeNotFound(ex);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("SWIFT code not found", response.getBody().get("message"));
    }

    @Test
    void handleISOCodeNotFound() {
        ISOCodeNotFound ex = new ISOCodeNotFound("ISO code not found");
        ResponseEntity<Map<String, String>> response = controller.handleISOCodeNotFound(ex);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("ISO code not found", response.getBody().get("message"));
    }

    @Test
    void handleSwiftCodeAlreadyExists() {
        SwiftCodeAlreadyExists ex = new SwiftCodeAlreadyExists("SWIFT code already exists");
        ResponseEntity<Map<String, String>> response = controller.handleSwiftCodeAlreadyExists(ex);

        assertEquals(409, response.getStatusCodeValue());
        assertEquals("SWIFT code already exists", response.getBody().get("message"));
    }
}
