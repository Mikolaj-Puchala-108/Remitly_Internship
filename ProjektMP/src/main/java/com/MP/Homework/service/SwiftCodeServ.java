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
 * Serwis obsługujący operacje związane z kodami SWIFT.
 *
 * Odpowiada za logikę biznesową związaną z pobieraniem, dodawaniem
 * i usuwaniem rekordów SWIFT, a także przekształcaniem danych do DTO.
 */
@Service
public class SwiftCodeServ {

    private final SwiftCodeRepo repository;

    /*
     * Konstruktor z wstrzykiwaniem repozytorium.
     *
     * @param repository repozytorium JPA dla encji SwiftCode
     */
    public SwiftCodeServ(SwiftCodeRepo repository) {

        this.repository = repository; //inject konstruktor
    }

    /*
     * Pobiera listę rekordów SWIFT na podstawie kodu ISO2 kraju.
     *
     * @param countryISO2 dwuliterowy kod kraju (np. "PL")
     * @return lista obiektów {@link SwiftCode}
     */
    public List<SwiftCode> getByCountry(String countryISO2) {
        return repository.findByCountryISO2(countryISO2.toUpperCase());
    }

    /*
     * Zwraca listę oddziałów dla danej centrali na podstawie kodu SWIFT.
     *
     * @param headCode pełny kod SWIFT centrali
     * @return lista oddziałów jako {@link SwiftCode}
     */
    public List<SwiftCode> getBranches(String headCode) {
        return repository.findBySwiftCodeStartingWith(headCode.substring(0, 8))//pierwsze 8 znakow
                .stream()
                .filter(sc -> !sc.getSwiftCode().toUpperCase().equals(headCode.toUpperCase()))
                .collect(Collectors.toList());
    }


//endpoint1
    /*
     * Zwraca szczegóły oddziału lub centrali na podstawie kodu SWIFT.
     * Zwraca obiekt {@link SwiftHQ} jeśli to centrala, albo {@link SwiftBranchSolo} jeśli nie.
     *
     * @param swiftCode kod SWIFT
     * @return DTO z danymi oddziału/centrali
     * @throws SwiftCodeNotFound jeśli kod SWIFT nie istnieje
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
     * Maper oddziału niebędącego centralą do DTO {@link SwiftBranchSolo}.
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
     * Maper oddziału do DTO {@link SwiftBranch}.
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
     * Pobiera wszystkie rekordy SWIFT dla danego kraju i mapuje do DTO {@link SwiftCountry}.
     *
     * @param countryISO2 kod ISO2 kraju
     * @return obiekt {@link SwiftCountry} z listą banków
     * @throws ISOCodeNotFound jeśli brak danych
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
     * Dodaje nowy kod SWIFT do bazy danych.
     *
     * @param create dane wejściowe w formacie {@link SwiftCreate}
     * @return komunikat o sukcesie
     * @throws SwiftCodeAlreadyExists jeśli kod już istnieje
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
     * Usuwa kod SWIFT z bazy danych.
     *
     * @param swiftCode kod SWIFT do usunięcia
     * @return komunikat o sukcesie
     * @throws SwiftCodeNotFound jeśli kod nie istnieje
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
