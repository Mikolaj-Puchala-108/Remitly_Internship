package com.MP.Homework.service;

import com.MP.Homework.data.SwiftCreate;
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
public class SwiftCodeServIntegrationTest {

    @Autowired
    private SwiftCodeServ service;

    @Autowired
    private SwiftCodeRepo repository;

    @Test
    void testAddGetDeleteSwiftCode() {
        SwiftCreate create = new SwiftCreate();
        create.setSwiftCode("TEST1234567");
        create.setAddress("Test Street");
        create.setBankName("Test Bank");
        create.setCountryISO("PL");
        create.setCountryName("POLAND");
        create.setHQ(true);


        String msg = service.addSwift(create);
        assertEquals("New SWIFT code has been saved", msg);


        Object result = service.getDetails("TEST1234567");
        assertNotNull(result);


        String deleted = service.deleteSwift("TEST1234567");
        assertEquals("Deleted TEST1234567", deleted);
    }
}
