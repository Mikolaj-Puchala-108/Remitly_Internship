package com.MP.Homework.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/*
 * Represents a bank branch in the SWIFT system.
 *
 * This class contains basic information about a bank branch, such as address,
 * bank name, country code, whether it is a head office and SWIFT code.
 *
 * The {@JsonPropertyOrder} annotation specifies the order in which fields are serialized
 * to JSON format
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
     * Bank branch address.
     */
    private String address;

    /*
     * Name of the bank branch.
     */
    private String bankName;

    /*
     * Bank ISO2 code.
     */
    private String countryISO2;

    /*
     * Determines if the bank is HQ.
     */
    protected boolean isHeadquarter;

    /*
     * Bank Swift Code.
     */
    private String swiftCode;


    /*
     * Returns the bank branch address.
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /*
     * Sets the bank branch address.
     *
     * @param address new address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /*
     * Returns the bank name.
     *
     * @return bank nam
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
     * Sets the bank name.
     *
     * @param bankName new bank name
     */
    public String getCountryISO2() {
        return countryISO2;
    }

    /*
     * Sets the country code in ISO2 format.
     *
     * @param countryISO2 the ISO2 country code
     */
    public void setCountryISO2(String countryISO2) {
        this.countryISO2 = countryISO2;
    }

    /*
     * Returns information whether the branch is the bank's headquarters.
     *
     * @return {@code true} if it is the headquarters, otherwise {@code false}
     */
    public boolean getIsHeadquarter() {
        return isHeadquarter;
    }

    /*
     * Sets whether the branch is the bank's headquarters.
     *
     * @param headquarter {@code true} if it is the headquarters     */
    public void setIsHeadquarter(boolean headquarter) {
        isHeadquarter = headquarter;
    }

    /*
     * Returns the branch's SWIFT code.
     *
     * @return SWIFT code
     */
    public String getSwiftCode() {
        return swiftCode;
    }

    /*
     * Sets the branch's SWIFT code.
     *
     * @param swiftCode new SWIFT code
     */
    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }


}
