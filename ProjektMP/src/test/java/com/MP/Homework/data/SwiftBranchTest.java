package com.MP.Homework.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SwiftBranchTest {

    @Test
    void setAndGetCorrectly() {
        SwiftBranch branch = new SwiftBranch();

        branch.setAddress("Test Street 01");
        branch.setBankName("Test Bank");
        branch.setCountryISO2("PL");
        branch.setIsHeadquarter(true);
        branch.setSwiftCode("TESTPL01");

        assertEquals("Test Street 01", branch.getAddress());
        assertEquals("Test Bank", branch.getBankName());
        assertEquals("PL", branch.getCountryISO2());
        assertTrue(branch.getIsHeadquarter());
        assertEquals("TESTPL01", branch.getSwiftCode());


    }
}