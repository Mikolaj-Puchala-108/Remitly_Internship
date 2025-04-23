package com.MP.Homework.data;
import jakarta.validation.constraints.*;

/*
 * Represents the input data required to create a new bank branch in the SWIFT system.
 *
 * The class contains validation annotations from the Jakarta Bean Validation library
 * (e.g. @NotBlank, @Pattern), which are used to verify the correctness of data sent
 * by the user, e.g. in forms or REST requests.
 */
public class SwiftCreate {

    /*
     * Bank branch address.
     * Cannot be empty
     */
    @NotBlank(message = "Address cannot be empty")
    public String address;

    /*
     * Bank name.
     * Cannot be empty.
     */
    @NotBlank(message = "Bank name cannot be empty")
    public String bankName;

    /*
     * Country code in ISO2
     * Must be 2 uppercase letters (e.g. "PL").
     */
    @Pattern(regexp = "^[A-Z]{2}$", message = "The country code should consist of 2 capital letters")
    public String countryISO2;


    /*
     * Full country name.
     * Cannot be empty.
     */
    @NotBlank(message = "Country name cannot be empty")
    public String CountryName;

    /*
     * Flag indicating whether the branch is the bank's headquarters. Used when creating a new bank
     */
    public boolean isHQ;

    /*
     * SWIFT code.
     * Must be exactly 11 characters, consisting of uppercase letters and/or numbers.
     */
    @Pattern(regexp = "^[A-Z0-9]{11}$", message = "SWIFT should be 11 characters long and should be uppercase")
    private String swiftCode;

// Getters and setters

    /*
     * Returns the branch address.
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /*
     * Sets the branch address.
     *
     * @param address branch address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /*
     * Returns the bank name.
     *
     * @return the bank name
     */
    public String getBankName() {
        return bankName;
    }

    /*
     * Sets the bank name.
     *
     * @param bankName the bank name
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /*
     * Returns the country code in ISO format.
     *
     * @return the ISO2 country code
     */
    public String getCountryISO2() {
        return countryISO2;
    }

    /*
     * Sets the country code in ISO format.
     *
     * @param countryISO country code
     */
    public void setCountryISO(String countryISO) {
        this.countryISO2 = countryISO;
    }

    /*
     * Returns the country name.
     *
     * @return country name
     */
    public String getCountryName() {
        return CountryName;
    }

    /*
     * Sets the country name.
     *
     * @param countryName country name
     */
    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    /*
     * Returns information whether the branch is a headquarters.
     *
     * @return {@code true} if it is a headquarters
     */
    public boolean getHQ() {
        return isHQ;
    }

    /*
     * Sets whether the branch is a HQ.
     *
     * @param isHQ {@code true} if HQ
     */
    public void setHQ(boolean isHQ) {
        this.isHQ = isHQ;
    }

    /*
     * Returns the SWIFT code.
     *
     * @return the SWIFT code
     */
    public String getSwiftCode() {
        return swiftCode;
    }

    /*
     * Sets the SWIFT code.
     *
     * @param swiftCode SWIFT code
     */
    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }
}
