package com.MP.Homework.rest;

import com.MP.Homework.Exception.ISOCodeNotFound;
import com.MP.Homework.Exception.SwiftCodeAlreadyExists;
import com.MP.Homework.Exception.SwiftCodeNotFound;
import com.MP.Homework.data.SwiftCreate;
import com.MP.Homework.entity.SwiftCode;
import com.MP.Homework.service.SwiftCodeServ;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/v1/swift-codes")
public class SwiftCodeRESTController {
    private final SwiftCodeServ service;

    public SwiftCodeRESTController(SwiftCodeServ service) {
        this.service = service;
    }

    @GetMapping("/{swiftCode}")
    public ResponseEntity<?> getSwiftDetails(@PathVariable String swiftCode) {
        try {
            Object data = service.getDetails(swiftCode);
            return ResponseEntity.ok(data);
        } catch (SwiftCodeNotFound ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", ex.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Unexpected error: " + e.getMessage()));
        }
    }


    @GetMapping("/country/{countryISO2}")
    public ResponseEntity<?> getISODetails(@PathVariable String countryISO2){
        try {
            Object data = service.getCountry(countryISO2);
            return ResponseEntity.ok(data);
        } catch (ISOCodeNotFound ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", ex.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Unexpected error: " + e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> addSwift(@RequestBody @Valid SwiftCreate create){
        try{
            String message = service.addSwift(create);
            return ResponseEntity.ok().body(Map.of("message", message));
        } catch (SwiftCodeAlreadyExists ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", ex.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Unexpected error: " + e.getMessage()));
        }
    }


    @DeleteMapping("/{swiftCode}")
    public ResponseEntity<?> deleteSwift(@PathVariable String swiftCode){
        try{
            String message = service.deleteSwift(swiftCode);
            return ResponseEntity.ok().body(Map.of("message", message));
        } catch (SwiftCodeNotFound ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", ex.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Unexpected error: " + e.getMessage()));
        }
    }


}





