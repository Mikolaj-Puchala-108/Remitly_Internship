package com.MP.Homework.repo;
import com.MP.Homework.entity.SwiftCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * JPA repository for the {@link SwiftCode} entity.
 * Enables CRUD operations and query methods based on Spring Data conventions.
 */
@Repository
public interface SwiftCodeRepo extends JpaRepository<SwiftCode, String> {

    /*
     * Returns a list of SWIFT codes belonging to a given country based on the ISO2 code.
     *
     * @param countryISO2 two-letter country code (e.g., "PL", "DE")
     * @return list of matching SWIFT records
     */
    List<SwiftCode> findByCountryISO2(String countryISO2);

    /*
     * Returns a list of SWIFT codes that start with the given prefix.
     * Used to find bank branches based on the first 8 characters of the head office’s SWIFT code.
     *
     * @param headCode first 8 characters of the SWIFT code (BIC without branch code)
     * @return list of branches matching the given prefix
     */
    List<SwiftCode> findBySwiftCodeStartingWith(String headCode);
}

