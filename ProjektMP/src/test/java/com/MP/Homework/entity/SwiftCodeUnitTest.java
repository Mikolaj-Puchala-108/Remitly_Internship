package com.MP.Homework.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SwiftCodeUnitTest {

    @Test
    void testGetSet() {
        SwiftCode swiftCode = new SwiftCode();
        swiftCode.setSwiftCode("TESTPL01XXX");
        swiftCode.setAddress("Address");
        swiftCode.setName("Bank");
        swiftCode.setTownName("Krakow");
        swiftCode.setCountryISO2("PL");
        swiftCode.setCountryName("Poland");
        swiftCode.setTimeZone("CET");
        swiftCode.setHQ(true);

        assertEquals("TESTPL01XXX", swiftCode.getSwiftCode());
        assertEquals("Address", swiftCode.getAddress());
        assertEquals("Bank", swiftCode.getName());
        assertEquals("Krakow", swiftCode.getTownName());
        assertEquals("PL", swiftCode.getCountryISO2());
        assertEquals("Poland", swiftCode.getCountryName());
        assertEquals("CET", swiftCode.getTimeZone());
        assertTrue(swiftCode.isHQ());
    }


    @Test
    void testHQTrue(){
        SwiftCode swiftCode = new SwiftCode();
        swiftCode.setSwiftCode("TESTPL01XXX");

        assertTrue(swiftCode.isHeadquarter(), "Swift code ending XXX is a HQ");
    }

    @Test
    void testHQFalse(){
        SwiftCode swiftCode = new SwiftCode();
        swiftCode.setSwiftCode("TESTPL01YYY");

        assertFalse(swiftCode.isHeadquarter(), "Swift code ending XXX is not a HQ");
    }
}
