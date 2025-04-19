package com.MP.Homework.rest;


import com.MP.Homework.Exception.SwiftCodeNotFound;
import com.MP.Homework.data.SwiftCreate;
import com.MP.Homework.service.SwiftCodeServ;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class SwiftCodeRESTControllerUnitTest {
    SwiftCodeServ mockService = Mockito.mock(SwiftCodeServ.class);
    SwiftCodeRESTController controller = new SwiftCodeRESTController(mockService);


    @Test
    void returnDataSwiftCode() throws Exception {
        when(mockService.getDetails("ABC")).thenReturn(Map.of("swiftCode", "ABC"));

        ResponseEntity<?> response = controller.getSwiftDetails("ABC");

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("ABC"));
    }

    @Test
    void returnWhenSwiftCodeNotFound() throws Exception {
        when(mockService.getDetails("XYZ")).thenThrow(new SwiftCodeNotFound("Not found"));

        ResponseEntity<?> response = controller.getSwiftDetails("XYZ");

        assertEquals(404, response.getStatusCodeValue());
        assertEquals(Map.of("message", "Not found"), response.getBody());
    }

    @Test
    void returnMessageAddSwift() throws Exception {
        SwiftCreate create = new SwiftCreate();
        create.setAddress("Test Street");
        create.setBankName("Test Bank");
        create.setCountryISO("PL");
        create.setCountryName("Polska");
        create.setSwiftCode("ABCDEF12XXX");

        when(mockService.addSwift(create)).thenReturn("Added successfully");

        ResponseEntity<?> response = controller.addSwift(create);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(Map.of("message", "Added successfully"), response.getBody());
    }

    @Test
    void returnMessageDeleteSwift() throws Exception {
        when(mockService.deleteSwift("ABC123")).thenReturn("Deleted");

        ResponseEntity<?> response = controller.deleteSwift("ABC123");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(Map.of("message", "Deleted"), response.getBody());
    }



}
