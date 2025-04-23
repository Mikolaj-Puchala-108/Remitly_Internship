package com.MP.Homework.service;

import com.MP.Homework.data.SwiftCreate;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class ExceptionControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSwiftCodeNotFound() throws Exception {
        mockMvc.perform(get("/v1/swift-codes/NONSENSECODE"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("SWIFT code not found"));
    }

    @Test
    void testInvalidSwiftCreate() throws Exception {
        SwiftCreate invalid = new SwiftCreate();
        invalid.setSwiftCode("123");
        invalid.setBankName("");
        invalid.setAddress("");
        invalid.setCountryISO("XXX");
        invalid.setCountryName("");
        invalid.setHQ(true);

        mockMvc.perform(post("/v1/swift-codes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.swiftCode").value("SWIFT should be 11 characters long and should be uppercase"));
    }

    @Test
    void testSwiftCodeAlreadyExists() throws Exception {
        SwiftCreate create = new SwiftCreate();
        create.setSwiftCode("DUPLICATE12");
        create.setBankName("TestBank");
        create.setAddress("Test Address");
        create.setCountryISO("PL");
        create.setCountryName("Polska");
        create.setHQ(true);

        mockMvc.perform(post("/v1/swift-codes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(create)))
                .andExpect(status().isOk());

        mockMvc.perform(post("/v1/swift-codes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(create)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("SWIFT code already exists"));
    }
}
