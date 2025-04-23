package com.MP.Homework.entity;

import com.MP.Homework.repo.SwiftCodeRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class SwiftCodeIntegrationTest {

    @Autowired
    private SwiftCodeRepo repository;

    @Test
    void testSaveAndFindSwiftCode() {
        SwiftCode swiftCode = new SwiftCode();
        swiftCode.setSwiftCode("TESTPL01XXX");
        swiftCode.setAddress("Test Address");
        swiftCode.setName("Test Bank");
        swiftCode.setTownName("Test Town");
        swiftCode.setCountryISO2("PL");
        swiftCode.setCountryName("Poland");
        swiftCode.setHQ(true);

        repository.save(swiftCode);

        SwiftCode found = repository.findById("TESTPL01XXX").orElse(null);

        assertNotNull(found);
        assertEquals("Test Bank", found.getName());
        assertTrue(found.isHQ());
    }
}

