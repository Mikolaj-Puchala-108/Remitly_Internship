package com.MP.Homework.data;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SwiftCountryTest {

    @Test
    void setGetCorrectly() {
        SwiftCountry country = new SwiftCountry();

        country.setCountryName("Poland");
        country.setCountryISO2("PL");

        assertEquals("PL", country.getCountryISO2());
        assertEquals("Poland", country.getCountryName());


    }



    @Test
    void testBranches() {
        SwiftBranch branch1 = new SwiftBranch();
        branch1.setSwiftCode("TESTPL01XXX");

        SwiftBranch branch2 = new SwiftBranch();
        branch2.setSwiftCode("TESTPL02XXX");

        List<SwiftBranch> branches = List.of(branch1, branch2);

        SwiftCountry country = new SwiftCountry();
        country.setSwiftCodes(branches);

        assertNotNull(country.getBranches());
        assertEquals(2, country.getBranches().size());
        assertEquals("TESTPL01XXX", country.getBranches().get(0).getSwiftCode());
    }
}
