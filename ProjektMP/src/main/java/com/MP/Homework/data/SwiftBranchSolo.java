package com.MP.Homework.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/*
 * Reprezentuje oddział banku w systemie SWIFT jeśli jest wyświetlany solo.
 *
 * Klasa ta zawiera podstawowe informacje o oddziale bankowym, takie jak adres,
 * nazwa banku, kod kraju, nazwa kraju, informacja czy jest to centrala oraz kod SWIFT.
 * Dziedziczy po SwiftBranch dodatkowo wszystko oprócz nazwy kraju.
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
        "swiftCode"
})
public class SwiftBranchSolo extends SwiftBranch{

    /*
     * Nazwa kraju.
     */
    private  String countryName;

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
}
