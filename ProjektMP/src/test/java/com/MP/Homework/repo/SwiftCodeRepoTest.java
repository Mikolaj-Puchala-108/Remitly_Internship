package com.MP.Homework.repo;

import com.MP.Homework.entity.SwiftCode;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class SwiftCodeRepoTest {


    @Autowired
    private SwiftCodeRepo repository;

    @Test
    void saveFindDeleteSwiftCode() {
        SwiftCode swiftCode = new SwiftCode();
        swiftCode.setSwiftCode("ABCPL123XXX");
        swiftCode.setName("Test Bank");
        swiftCode.setAddress("Test Street");
        swiftCode.setTownName("Warsaw");
        swiftCode.setCountryISO2("PL");
        swiftCode.setCountryName("Poland");
        swiftCode.setHQ(true);

        repository.save(swiftCode);

        List<SwiftCode> plCodes = repository.findByCountryISO2("PL");
        assertFalse(plCodes.isEmpty());
        assertEquals("PL", plCodes.get(0).getCountryISO2());

        List<SwiftCode> prefixCodes = repository.findBySwiftCodeStartingWith("ABC");
        assertFalse(prefixCodes.isEmpty());
        assertTrue(prefixCodes.get(0).getSwiftCode().startsWith("ABC"));

        repository.deleteById("ABCPL123XXX");
        assertTrue(repository.findById("ABCPL123XXX").isEmpty());
    }
}
