package com.MP.Homework.data;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SwiftHQTest {
    @Test
    void testSettersAndGetters() {
        SwiftHQ hq = new SwiftHQ();

        hq.setSwiftCode("TESTPL01XXX");
        hq.setBankName("Test Bank");
        hq.setAddress("Test St");
        hq.setCountryISO2("PL");
        hq.setCountryName("Poland");
        hq.setIsHeadquarter(true);



        assertEquals("TESTPL01XXX", hq.getSwiftCode());
        assertEquals("Test Bank", hq.getBankName());
        assertEquals("Test St", hq.getAddress());
        assertEquals("PL", hq.getCountryISO2());
        assertEquals("Poland", hq.getCountryName());
        assertTrue(hq.getIsHeadquarter());
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
