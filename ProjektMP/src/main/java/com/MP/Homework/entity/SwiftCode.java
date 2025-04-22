package com.MP.Homework.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

/*
 * Encja reprezentująca oddział bankowy w tabeli {banks}.
 *
 * Zawiera dane identyfikujące oddział, takie jak kod SWIFT, nazwa banku,
 * adres, miasto, kraj, strefa czasowa oraz informacja, czy to centrala.
 *
 * Zastosowano adnotacje JPA do mapowania pól na odpowiednie kolumny w bazie danych,
 * oraz adnotacje Jackson do kontrolowania serializacji JSON.
 */

@Entity
@Table(name = "banks")
public class SwiftCode {

    /*
     * Kod kraju w formacie ISO 3166-1 alpha-2.
     * Mapped to column "COUNTRY ISO2 CODE".
     */
    @Column(name = "`COUNTRY ISO2 CODE`")
    private String countryISO2;

    /*
     * Kod SWIFT oddziału – klucz główny.
     * Mapped to column "SWIFT CODE".
     */
    @Id
    @Column(name = "`SWIFT CODE`")
    private String swiftCode;

    /*
     * Nazwa banku.
     */
    private String name;

    /*
     * Adres oddziału.
     */
    private String address;

    /*
     * Nazwa miasta.
     * Mapped to column "TOWN NAME".
     */
    @Column(name = "`TOWN NAME`")
    private String townName;

    /*
     * Pełna nazwa kraju.
     * Mapped to column "COUNTRY NAME".
     */
    @Column(name = "`COUNTRY NAME`")
    private String countryName;

    /*
     * Strefa czasowa banku.
     * Mapped to column "TIME ZONE".
     */
    @Column(name = "`TIME ZONE`")
    private String timeZone;

    /*
     * Flaga określająca, czy oddział jest centralą banku.
     */
    private boolean isHQ;

    /*
     * Konstruktor domyślny wymagany przez JPA.
     */
    public SwiftCode() {}


    /*
     * Konstruktor z wszystkimi polami poza isHQ.
     *
     * @param countryISO2 Kod kraju
     * @param SwiftCode Kod SWIFT
     * @param Name Nazwa banku
     * @param Address Adres
     * @param TownName Miasto
     * @param CountryName Nazwa kraju
     * @param TimeZone Strefa czasowa
     */
    public SwiftCode(String countryISO2, String SwiftCode, String Name, String Address, String TownName, String CountryName, String TimeZone) {
        this.countryISO2 = countryISO2;
        this.swiftCode = SwiftCode;
        this.name = Name;
        this.address = Address;
        this.townName = TownName;
        this.countryName = CountryName;
        this.timeZone = TimeZone;
    }



    /*
     * Zwraca dwuliterowy kod ISO2 kraju (np. "PL", "US").
     *
     * @return kod kraju ISO2
     */
    @JsonProperty("countryISO2")
    public String getCountryISO2() {
        return countryISO2;
    }

    /*
     * Ustawia dwuliterowy kod ISO2 kraju.
     *
     * @param countryISO2 kod kraju ISO2
     */
    public void setCountryISO2(String countryISO2) {
        this.countryISO2 = countryISO2;
    }

    /*
     * Zwraca kod SWIFT (BIC) banku.
     *
     * @return kod SWIFT
     */
    @JsonProperty("swiftCode")
    public String getSwiftCode() {
        return swiftCode;
    }

    /*
     * Ustawia kod SWIFT (BIC) banku.
     *
     * @param swiftCode kod SWIFT
     */
    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    /*
     * Zwraca nazwę banku.
     *
     * @return nazwa banku
     */
    @JsonProperty("bankName")
    public String getName() {
        return name;
    }

    /*
     * Ustawia nazwę banku.
     *
     * @param name nazwa banku
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * Zwraca adres banku.
     *
     * @return adres
     */
    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    /*
     * Ustawia adres banku.
     *
     * @param address adres
     */
    public void setAddress(String address) {
        this.address = address;
    }


    /*
     * Zwraca nazwę miasta, w którym znajduje się bank.
     *
     * @return nazwa miasta
     */
    @JsonProperty("townName")
    public String getTownName() {
        return townName;
    }

    /*
     * Ustawia nazwę miasta banku.
     *
     * @param townName nazwa miasta
     */
    public void setTownName(String townName) {
        this.townName = townName;
    }

    /*
     * Zwraca pełną nazwę kraju.
     *
     * @return nazwa kraju
     */
    @JsonProperty("countryName")
    public String getCountryName() {
        return countryName;
    }

    /*
     * Ustawia pełną nazwę kraju.
     *
     * @param countryName nazwa kraju
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /*
     * Zwraca strefę czasową banku (np. "Europe/Warsaw").
     *
     * @return strefa czasowa
     */
    @JsonProperty("timeZone")
    public String getTimeZone() {
        return timeZone;
    }

    /*
     * Ustawia strefę czasową banku.
     *
     * @param timeZone strefa czasowa
     */
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    /*
     * Sprawdza, czy dany oddział to centrala (HQ).
     *
     * @return true jeśli to centrala
     */
    public boolean isHQ() {
        return isHQ;
    }

    /*
     * Ustawia, czy dany oddział jest centralą.
     *
     * @param HQ true jeśli centrala
     */
    public void setHQ(boolean HQ) {
        isHQ = HQ;
    }

    /*
     * Zwraca informację, czy oddział jest centralą banku,
     * na podstawie końcówki kodu SWIFT ("XXX").
     *
     * @return {true} jeśli kod SWIFT kończy się na "XXX"
     */
    @JsonProperty("isHeadquarter")
    public boolean isHeadquarter() {
        return swiftCode != null && (swiftCode.toUpperCase().endsWith("XXX"));
    }

}