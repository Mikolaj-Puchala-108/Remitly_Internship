package com.MP.Homework.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/*
 * Reprezentuje oddział banku w systemie SWIFT jeśli jest wyświetlany jako HQ.
 *
 * Klasa ta zawiera podstawowe informacje o oddziale bankowym, takie jak adres,
 * nazwa banku, kod kraju, nazwa kraju, informacja czy jest to centrala, liste kodów swift oraz kod SWIFT.
 * Dziedziczy po SwiftBranch wszystko oprócz nazwy kraju oraz listy kodów swift podlegających pod HQ.
 *
 * Adnotacja {@JsonPropertyOrder} określa kolejność serializacji pól
 * do formatu JSON.
 */
@JsonPropertyOrder({
        "address",
        "bankName",
        "countryISO2",
        "countryName",
        "isHeadquarter",
        "swiftCode",
        "branches"
})
public class SwiftHQ extends SwiftBranch {

    /*
     * Nazwa kraju.
     */
    private String countryName;

    /*
     * Lista kodów swift oddziałów podlegających pod HQ.
     */
    private List<SwiftBranch> branches;

    /*
     * Zwraca nazwe kraju.
     *
     * @return countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /*
     * Ustawia nazwe kraju.
     *
     * @param countryName nowa nazwa
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /*
     * Zwraca podlegające banki z listy
     *
     * @return liste banków swift
     */
    public List<SwiftBranch> getBranches() {
        return branches;
    }

    /*
     * Ustawia podlegające banki z listy
     *
     * @param swiftCodes element listy
     */
    public void setBranches(List<SwiftBranch> branches) {
        this.branches = branches;
    }
}

