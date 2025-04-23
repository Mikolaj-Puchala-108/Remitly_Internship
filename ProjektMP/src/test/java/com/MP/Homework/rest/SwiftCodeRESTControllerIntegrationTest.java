package com.MP.Homework.rest;

import com.MP.Homework.data.SwiftCreate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class SwiftCodeRESTControllerIntegrationTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void returnNotFoundSwiftCode() throws Exception {
        mockMvc.perform(get("/v1/swift-codes/UNKNOWN"))
                .andExpect(status().isNotFound());
    }

    @Test
    void rejectInvalidSwiftCode() throws Exception {
        SwiftCreate invalid = new SwiftCreate();
        invalid.setAddress("");
        invalid.setBankName("");
        invalid.setCountryISO("XXX");
        invalid.setCountryName("");
        invalid.setSwiftCode("123");

        mockMvc.perform(post("/v1/swift-codes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest());
    }





}
