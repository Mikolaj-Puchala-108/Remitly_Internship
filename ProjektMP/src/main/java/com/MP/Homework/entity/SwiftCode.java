package com.MP.Homework.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

/*
 * Entity representing a bank branch in the {banks} table.
 *
 * Contains identifying data such as SWIFT code, bank name,
 * address, city, country, time zone, and a flag indicating whether it is the head office.
 *
 * JPA annotations are used for mapping fields to the corresponding database columns,
 * and Jackson annotations for controlling JSON serialization.
 */

@Entity
@Table(name = "banks")
public class SwiftCode {

    /*
     * Country code in ISO 3166-1 alpha-2 format.
     * Mapped to column "COUNTRY ISO2 CODE".
     */
    @Column(name = "`COUNTRY ISO2 CODE`")
    private String countryISO2;

    /*
     * SWIFT code of the branch – primary key.
     * Mapped to column "SWIFT CODE".
     */
    @Id
    @Column(name = "`SWIFT CODE`")
    private String swiftCode;

    /*
     * Bank name.
     */
    private String name;

    /*
     * Branch address.
     */
    private String address;

    /*
     * City name.
     * Mapped to column "TOWN NAME".
     */
    @Column(name = "`TOWN NAME`")
    private String townName;

    /*
     * Full country name.
     * Mapped to column "COUNTRY NAME".
     */
    @Column(name = "`COUNTRY NAME`")
    private String countryName;

    /*
     * Bank's time zone.
     * Mapped to column "TIME ZONE".
     */
    @Column(name = "`TIME ZONE`")
    private String timeZone;

    /*
     * Flag indicating whether the branch is the bank’s headquarters.
     */
    private boolean isHQ;

    /*
     * Default constructor required by JPA.
     */
    public SwiftCode() {}


    /*
     * Constructor with all fields except isHQ.
     *
     * @param countryISO2 Country code
     * @param SwiftCode SWIFT code
     * @param Name Bank name
     * @param Address Address
     * @param TownName City
     * @param CountryName Country name
     * @param TimeZone Time zone
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
     * Returns the two-letter ISO2 country code (e.g., "PL", "US").
     *
     * @return ISO2 country code
     */
    @JsonProperty("countryISO2")
    public String getCountryISO2() {
        return countryISO2;
    }

    /*
     * Sets the two-letter ISO2 country code.
     *
     * @param countryISO2 ISO2 country code
     */
    public void setCountryISO2(String countryISO2) {
        this.countryISO2 = countryISO2;
    }

    /*
     * Returns the bank’s SWIFT (BIC) code.
     *
     * @return SWIFT code
     */
    @JsonProperty("swiftCode")
    public String getSwiftCode() {
        return swiftCode;
    }

    /*
     * Sets the bank’s SWIFT (BIC) code.
     *
     * @param swiftCode SWIFT code
     */
    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    /*
     * Returns the bank name.
     *
     * @return bank name
     */
    @JsonProperty("bankName")
    public String getName() {
        return name;
    }

    /*
     * Sets the bank name.
     *
     * @param name bank name
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * Returns the bank address.
     *
     * @return address
     */
    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    /*
     * Sets the bank address.
     *
     * @param address address
     */
    public void setAddress(String address) {
        this.address = address;
    }


    /*
     * Returns the city where the bank is located.
     *
     * @return city name
     */
    @JsonProperty("townName")
    public String getTownName() {
        return townName;
    }

    /*
     * Sets the city name.
     *
     * @param townName city name
     */
    public void setTownName(String townName) {
        this.townName = townName;
    }

    /*
     * Returns the full country name.
     *
     * @return country name
     */
    @JsonProperty("countryName")
    public String getCountryName() {
        return countryName;
    }

    /*
     * Sets the full country name.
     *
     * @param countryName country name
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /*
     * Returns the bank’s time zone (e.g., "Europe/Warsaw").
     *
     * @return time zone
     */
    @JsonProperty("timeZone")
    public String getTimeZone() {
        return timeZone;
    }

    /*
     * Sets the bank’s time zone.
     *
     * @param timeZone time zone
     */
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    /*
     * Checks whether the branch is the bank’s headquarters.
     *
     * @return true if it is the headquarters
     */
    public boolean isHQ() {
        return isHQ;
    }

    /*
     * Sets whether the branch is the headquarters.
     *
     * @param HQ true if headquarters
     */
    public void setHQ(boolean HQ) {
        isHQ = HQ;
    }

    /*
     * Returns whether the branch is the bank’s headquarters,
     * based on the SWIFT code ending ("XXX").
     *
     * @return {true} if the SWIFT code ends with "XXX"
     */
    @JsonProperty("isHeadquarter")
    public boolean isHeadquarter() {
        return swiftCode != null && (swiftCode.toUpperCase().endsWith("XXX"));
    }

}