package com.MP.Homework.service;


import com.MP.Homework.Exception.ISOCodeNotFound;
import com.MP.Homework.Exception.SwiftCodeAlreadyExists;
import com.MP.Homework.Exception.SwiftCodeNotFound;
import com.MP.Homework.data.SwiftCreate;
import com.MP.Homework.data.SwiftHQTest;
import com.MP.Homework.entity.SwiftCode;
import com.MP.Homework.repo.SwiftCodeRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class SwiftCodeServUnitTest {
    @Mock
    private SwiftCodeRepo repository;

    @InjectMocks
    private SwiftCodeServ service;

    private SwiftCode sampleCode;

    @BeforeEach
    void setUp() {
        sampleCode = new SwiftCode();
        sampleCode.setSwiftCode("AAAABBCCXXX");
        sampleCode.setAddress("TEST ADDRESS");
        sampleCode.setName("Test BANK");
        sampleCode.setCountryISO2("PL");
        sampleCode.setCountryName("POLAND");
        sampleCode.setHQ(true);
    }

    @Test
    void testGetDetailsHQSuccess() {
        when(repository.findById("AAAABBCCXXX")).thenReturn(Optional.of(sampleCode));
        when(repository.findBySwiftCodeStartingWith("AAAABBCC")).thenReturn(List.of());

        Object result = service.getDetails("AAAABBCCXXX");

        assertNotNull(result);
        assertTrue(result instanceof SwiftHQTest);
        System.out.println("HQ flag in sampleCode: " + sampleCode.isHeadquarter());

    }

    @Test
    void testGetDetailsNotFound() {
        when(repository.findById("XYZ")).thenReturn(Optional.empty());

        assertThrows(SwiftCodeNotFound.class, () -> service.getDetails("XYZ"));
    }

    @Test
    void testAddSwiftSuccess() {
        SwiftCreate create = new SwiftCreate();
        create.setSwiftCode("NEW12345678");
        create.setAddress("New St");
        create.setBankName("New Bank");
        create.setCountryISO("PL");
        create.setCountryName("Poland");
        create.setHQ(true);

        when(repository.existsById("NEW12345678")).thenReturn(false);

        String msg = service.addSwift(create);

        assertEquals("New SWIFT code has been saved", msg);
        verify(repository).save(any(SwiftCode.class));
    }

    @Test
    void testAddSwiftAlreadyExists() {
        SwiftCreate create = new SwiftCreate();
        create.setSwiftCode("EXISTING1234");

        when(repository.existsById("EXISTING1234")).thenReturn(true);

        assertThrows(SwiftCodeAlreadyExists.class, () -> service.addSwift(create));
    }

    @Test
    void testDeleteSwiftSuccess() {
        when(repository.findById("DEL12345678")).thenReturn(Optional.of(sampleCode));

        String result = service.deleteSwift("DEL12345678");

        assertEquals("Deleted DEL12345678", result);
        verify(repository).deleteById("DEL12345678");
    }

    @Test
    void testDeleteSwiftNotFound() {
        when(repository.findById("XYZ")).thenReturn(Optional.empty());

        assertThrows(SwiftCodeNotFound.class, () -> service.deleteSwift("XYZ"));
    }

    @Test
    void testGetCountrySuccess() {
        when(repository.findByCountryISO2("PL")).thenReturn(List.of(sampleCode));

        var result = service.getCountry("PL");

        assertEquals("PL", result.getCountryISO2());
        assertEquals("POLAND", result.getCountryName());
        assertEquals(1, result.getBranches().size());
    }

    @Test
    void testGetCountryNotFound() {
        when(repository.findByCountryISO2("XX")).thenReturn(List.of());

        assertThrows(ISOCodeNotFound.class, () -> service.getCountry("XX"));
    }
}
