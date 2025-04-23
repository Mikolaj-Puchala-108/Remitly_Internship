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
 * REST API controller for SWIFT code operations.
 * Supports retrieving, creating, and deleting SWIFT codes.
 */
@RestController
@RequestMapping("/v1/swift-codes")
public class SwiftCodeRESTController {
    private final SwiftCodeServ service;

    /*
     * Constructor with service injection.
     *
     * @param service handling business logic
     */
    public SwiftCodeRESTController(SwiftCodeServ service) {
        this.service = service;
    }

    /*
     * Endpoint GET /v1/swift-codes/{swiftCode}
     * Retrieves details of a head office or branch based on the SWIFT code.
     *
     * @param swiftCode SWIFT code
     * @return details in format {@link com.MP.Homework.data.SwiftHQ} or {@link com.MP.Homework.data.SwiftBranchSolo}
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
     * Retrieves all banks in a given country based on ISO2 code.
     *
     * @param countryISO2 two-letter country code
     * @return data in format {@link com.MP.Homework.data.SwiftCountry}
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
     * Adds a new SWIFT code to the database.
     *
     * @param create input data validated with {@link jakarta.validation.Valid} annotations
     * @return success message
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
     * Deletes a SWIFT code from the database.
     *
     * @param swiftCode code to delete
     * @return deletion message
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





