package com.MP.Homework.rest;

import com.MP.Homework.Exception.ISOCodeNotFound;
import com.MP.Homework.Exception.SwiftCodeAlreadyExists;
import com.MP.Homework.Exception.SwiftCodeNotFound;
import com.MP.Homework.data.SwiftCreate;
import com.MP.Homework.service.SwiftCodeServ;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/*
 * REST API Controller dla operacji na kodach SWIFT.
 * Obsługuje żądania związane z pobieraniem, tworzeniem i usuwaniem kodów SWIFT.
 */
@RestController
@RequestMapping("/v1/swift-codes")
public class SwiftCodeRESTController {
    private final SwiftCodeServ service;

    /*
     * Konstruktor z wstrzykiwaniem serwisu.
     *
     * @param service serwis obsługujący logikę biznesową
     */
    public SwiftCodeRESTController(SwiftCodeServ service) {
        this.service = service;
    }

    /*
     * Endpoint GET /v1/swift-codes/{swiftCode}
     * Pobiera szczegóły centrali lub oddziału na podstawie kodu SWIFT.
     *
     * @param swiftCode kod SWIFT
     * @return szczegóły w formacie {@link com.MP.Homework.data.SwiftHQ} lub {@link com.MP.Homework.data.SwiftBranchSolo}
     */
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

    /*
     * Endpoint GET /v1/swift-codes/country/{countryISO2}
     * Pobiera wszystkie banki danego kraju według kodu ISO2.
     *
     * @param countryISO2 dwuliterowy kod kraju
     * @return dane w formacie {@link com.MP.Homework.data.SwiftCountry}
     */
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

    /*
     * Endpoint POST /v1/swift-codes
     * Dodaje nowy kod SWIFT do bazy danych.
     *
     * @param create dane wejściowe walidowane adnotacjami {@link jakarta.validation.Valid}
     * @return komunikat o powodzeniu
     */
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


    /*
     * Endpoint DELETE /v1/swift-codes/{swiftCode}
     * Usuwa kod SWIFT z bazy danych.
     *
     * @param swiftCode kod do usunięcia
     * @return komunikat o usunięciu
     */
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





