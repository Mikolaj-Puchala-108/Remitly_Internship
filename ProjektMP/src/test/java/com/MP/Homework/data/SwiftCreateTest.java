package com.MP.Homework.data;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SwiftCreateTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    public void failWhenInvalid() {
        SwiftCreate swiftCreate = new SwiftCreate();
        swiftCreate.setAddress("");
        swiftCreate.setBankName("");
        swiftCreate.setCountryISO("PLX");
        swiftCreate.setCountryName("");
        swiftCreate.setSwiftCode("XXX");

        Set<ConstraintViolation<SwiftCreate>> violations = validator.validate(swiftCreate);

        assertEquals(5, violations.size());

        for (ConstraintViolation<SwiftCreate> violation : violations) {
            System.out.println(violation.getPropertyPath() + ": " + violation.getMessage());
        }
    }

    @Test
    public void passWhenAllValid() {
        SwiftCreate swiftCreate = new SwiftCreate();
        swiftCreate.setAddress("Test Street");
        swiftCreate.setBankName("Test Bank");
        swiftCreate.setCountryISO("PL");
        swiftCreate.setCountryName("Poland");
        swiftCreate.setSwiftCode("ABCDEF12XXX");

        Set<ConstraintViolation<SwiftCreate>> violations = validator.validate(swiftCreate);

        assertTrue(violations.isEmpty(), "Validation should pass with no errors");
    }

    @Test
    public void failWhenSomeInvalid() {
        SwiftCreate swiftCreate = new SwiftCreate();
        swiftCreate.setAddress("Poland");
        swiftCreate.setBankName("");
        swiftCreate.setCountryISO("PLX");
        swiftCreate.setCountryName("");
        swiftCreate.setSwiftCode("XXX");

        Set<ConstraintViolation<SwiftCreate>> violations = validator.validate(swiftCreate);

        assertEquals(4, violations.size());

        for (ConstraintViolation<SwiftCreate> violation : violations) {
            System.out.println(violation.getPropertyPath() + ": " + violation.getMessage());
        }
    }
}




