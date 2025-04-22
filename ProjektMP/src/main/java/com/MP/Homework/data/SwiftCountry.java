package com.MP.Homework.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/*
 * Reprezentuje kraj w systemie SWIFT na podstwie kodu ISO.
 *
 * Klasa ta zawiera podstawowe informacje o kraju, takie jak ISO2,
 * nazwa kraju oraz kody swift w tym kraju
 *
 * Adnotacja {@JsonPropertyOrder} określa kolejność serializacji pól
 * do formatu JSON.
 */
@JsonPropertyOrder({
        "countryISO2",
        "countryName",
        "swiftCodes"
})
public class SwiftCountry{

    /*
     * Nazwa kraju.
     */
    private String countryName;

    /*
     * Kod ISO2 kraju.
     */
    private String countryISO2;

    /*
     * Lista kodów swift.
     */
    private List<SwiftBranch> swiftCodes;

    /*
     * Zwraca nazwe kraju.
     *
     * @return nazwę kraju
     */
    public String getCountryName() {
        return countryName;
    }

    /*
     * Ustawia nazwę kraju.
     *
     * @param countryName nowa nazwa kraju
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /*
     * Zwraca kod ISO2
     *
     * @return kod kraju ISO2
     */
    public String getCountryISO2() {
        return countryISO2;
    }

    /*
     * Ustawia kod kraju w formacie ISO2.
     *
     * @param countryISO2 kod kraju ISO2
     */
    public void setCountryISO2(String countryISO2) {
        this.countryISO2 = countryISO2;
    }

    /*
     * Zwraca kody swift do listy
     *
     * Adnotacja {@JsonProperty} ustawia w json nazwę parametru na swiftCodes\
     *
     * @return liste kodów swift
     */
    @JsonProperty("swiftCodes")
    public List<SwiftBranch> getBranches() {
        return swiftCodes;
    }

    /*
     * Ustawia kody swift z listy
     *
     * @param swiftCodes element listy kodów swift
     */
    public void setSwiftCodes(List<SwiftBranch> swiftCodes) {
        this.swiftCodes = swiftCodes;
    }
}
