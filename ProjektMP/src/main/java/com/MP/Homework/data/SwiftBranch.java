package com.MP.Homework.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/*
 * Reprezentuje oddział banku w systemie SWIFT.
 *
 * Klasa ta zawiera podstawowe informacje o oddziale bankowym, takie jak adres,
 * nazwa banku, kod kraju, informacja czy jest to centrala oraz kod SWIFT.
 *
 * Adnotacja {@JsonPropertyOrder} określa kolejność serializacji pól
 * do formatu JSON.
 */
@JsonPropertyOrder({
        "address",
        "bankName",
        "countryISO2",
        "isHeadquarter",
        "swiftCode"
})
public class SwiftBranch {

    /*
     * Adres oddziału banku.
     */
    private String address;

    /*
     * nazwe oddziału banku.
     */
    private String bankName;

    /*
     * Kod ISO2 banku.
     */
    private String countryISO2;

    /*
     * określa czy bank to HQ.
     */
    protected boolean isHeadquarter;

    /*
     * Kod Swift banku.
     */
    private String swiftCode;


    /*
     * Zwraca adres oddziału banku.
     *
     * @return adres
     */
    public String getAddress() {
        return address;
    }

    /*
     * Ustawia adres oddziału banku.
     *
     * @param address nowy adres
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /*
     * Zwraca nazwę banku.
     *
     * @return nazwa banku
     */
    public String getBankName() {
        return bankName;
    }

    /*
     * Ustawia nazwę banku.
     *
     * @param bankName nowa nazwa banku
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /*
     * Zwraca kod kraju w formacie ISO2.
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
     * Zwraca informację, czy oddział jest centralą banku.
     *
     * @return {@code true} jeśli to centrala, w przeciwnym razie {@code false}
     */
    public boolean getIsHeadquarter() {
        return isHeadquarter;
    }

    /*
     * Ustawia informację, czy oddział jest centralą banku.
     *
     * @param headquarter {@code true} jeśli to centrala
     */
    public void setIsHeadquarter(boolean headquarter) {
        isHeadquarter = headquarter;
    }

    /*
     * Zwraca kod SWIFT oddziału.
     *
     * @return kod SWIFT
     */
    public String getSwiftCode() {
        return swiftCode;
    }

    /*
     * Ustawia kod SWIFT oddziału.
     *
     * @param swiftCode nowy kod SWIFT
     */
    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }


}
