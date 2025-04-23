package com.MP.Homework.service;
import com.MP.Homework.Exception.ISOCodeNotFound;
import com.MP.Homework.Exception.SwiftCodeAlreadyExists;
import com.MP.Homework.Exception.SwiftCodeNotFound;
import com.MP.Homework.data.*;
import com.MP.Homework.entity.SwiftCode;
import com.MP.Homework.repo.SwiftCodeRepo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/*
 * Service handling operations related to SWIFT codes.
 *
 * Responsible for business logic regarding retrieval, addition,
 * and deletion of SWIFT records, as well as transforming data into DTOs.
 */
@Service
public class SwiftCodeServ {

    private final SwiftCodeRepo repository;

    /*
     * Constructor with repository injection.
     *
     * @param repository JPA repository for SwiftCode entity
     */
    public SwiftCodeServ(SwiftCodeRepo repository) {

        this.repository = repository; //inject konstruktor
    }

    /*
     * Retrieves a list of SWIFT records based on the country's ISO2 code.
     *
     * @param countryISO2 two-letter country code (e.g., "PL")
     * @return list of {@link SwiftCode} objects
     */
    public List<SwiftCode> getByCountry(String countryISO2) {
        return repository.findByCountryISO2(countryISO2.toUpperCase());
    }

    /*
     * Returns a list of branches for a given head office based on the SWIFT code.
     *
     * @param headCode full SWIFT code of the head office
     * @return list of branches as {@link SwiftCode}
     */
    public List<SwiftCode> getBranches(String headCode) {
        return repository.findBySwiftCodeStartingWith(headCode.substring(0, 8))//pierwsze 8 znakow
                .stream()
                .filter(sc -> !sc.getSwiftCode().toUpperCase().equals(headCode.toUpperCase()))
                .collect(Collectors.toList());
    }


//endpoint1
    /*
     * Returns details of a branch or head office based on the SWIFT code.
     * Returns {@link SwiftHQ} if it's a head office, or {@link SwiftBranchSolo} otherwise.
     *
     * @param swiftCode SWIFT code
     * @return DTO with branch/head office data
     * @throws SwiftCodeNotFound if the SWIFT code doesn't exist
     */
    public Object getDetails(String swiftCode) {
        Optional<SwiftCode> details = repository.findById(swiftCode);
        if (details.isEmpty()) {
            throw new SwiftCodeNotFound("SWIFT code not found");
        }

        SwiftCode main = details.get();

        if (main.isHeadquarter()) { //jesli centrala
            SwiftHQ data = new SwiftHQ();
            data.setSwiftCode(main.getSwiftCode().toUpperCase());
            data.setAddress(main.getAddress().toUpperCase());
            data.setBankName(main.getName().toUpperCase());
            data.setCountryISO2(main.getCountryISO2().toUpperCase());
            data.setCountryName(main.getCountryName().toUpperCase());
            data.setIsHeadquarter(true);

            List<SwiftBranch> branches = getBranches(main.getSwiftCode()).stream()//wypisuje branche
                    .map(this::mapToBranchHQ)
                    .collect(Collectors.toList());

            data.setBranches(branches);
            return data;
        } else { //jesli nie
            return mapToBranchB(main);
        }
    }

    /*
     * Maps a non-head office branch to {@link SwiftBranchSolo} DTO.
     */
    private SwiftBranchSolo mapToBranchB(SwiftCode code) {
        SwiftBranchSolo data = new SwiftBranchSolo();
        data.setSwiftCode(code.getSwiftCode().toUpperCase());
        data.setAddress(code.getAddress().toUpperCase());
        data.setBankName(code.getName().toUpperCase());
        data.setCountryISO2(code.getCountryISO2().toUpperCase());
        data.setCountryName(code.getCountryName().toUpperCase()); // ← tylko tu
        data.setIsHeadquarter(code.isHeadquarter());
        return data;
    }

    /*
     * Maps a branch to {@link SwiftBranch} DTO.
     */
    private SwiftBranch mapToBranchHQ(SwiftCode code) {
        SwiftBranch data = new SwiftBranch();
        data.setSwiftCode(code.getSwiftCode().toUpperCase());
        data.setAddress(code.getAddress().toUpperCase());
        data.setBankName(code.getName().toUpperCase());
        data.setCountryISO2(code.getCountryISO2().toUpperCase());
        data.setIsHeadquarter(code.isHeadquarter());
        return data;
    }

//endpoint2
/*
 * Retrieves all SWIFT records for a given country and maps them to {@link SwiftCountry} DTO.
 *
 * @param countryISO2 country ISO2 code
 * @return {@link SwiftCountry} object with list of banks
 * @throws ISOCodeNotFound if no data found
 */
    public SwiftCountry getCountry(String countryISO2) {
        List<SwiftCode> codes = getByCountry(countryISO2);

        if (codes.isEmpty()) {
            throw new ISOCodeNotFound("No SWIFT codes found for country ISO2");
        }

        SwiftCountry data = new SwiftCountry();
        data.setCountryISO2(countryISO2.toUpperCase());
        data.setCountryName(codes.get(0).getCountryName());

        List<SwiftBranch> swiftCodes = codes.stream()
                .map(this::mapToBranchHQ)
                .collect(Collectors.toList());

        data.setSwiftCodes(swiftCodes);

        return data;
    }


    //endpoint3
    /*
     * Adds a new SWIFT code to the database.
     *
     * @param create input data in {@link SwiftCreate} format
     * @return success message
     * @throws SwiftCodeAlreadyExists if the code already exists
     */
    public String addSwift(SwiftCreate create) {
        if (repository.existsById(create.getSwiftCode())) {
            throw new SwiftCodeAlreadyExists("SWIFT code already exists");
        }

        SwiftCode newSwift = new SwiftCode();


        newSwift.setSwiftCode(create.getSwiftCode().toUpperCase());
        newSwift.setAddress(create.getAddress().toUpperCase());
        newSwift.setName(create.getBankName().toUpperCase());
        newSwift.setCountryISO2(create.getCountryISO2().toUpperCase());
        newSwift.setCountryName(create.getCountryName().toUpperCase());
        newSwift.setHQ(create.getHQ());

        repository.save(newSwift);

        return "New SWIFT code has been saved";

    }


    //endpoint4
    /*
     * Deletes a SWIFT code from the database.
     *
     * @param swiftCode SWIFT code to delete
     * @return success message
     * @throws SwiftCodeNotFound if the code does not exist
     */
    public String deleteSwift(String swiftCode) {
        Optional<SwiftCode> details = repository.findById(swiftCode);

        if (details.isEmpty()) {
            throw new SwiftCodeNotFound("SWIFT code does not exist");
        }else{
            repository.deleteById(swiftCode);
            return "Deleted " + swiftCode.toUpperCase();
        }
    }
}
