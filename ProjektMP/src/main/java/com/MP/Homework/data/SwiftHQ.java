package com.MP.Homework.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/*
 * Represents a bank branch in the SWIFT system if displayed as HQ.
 *
 * This class contains basic information about a bank branch, such as address,
 * bank name, country code, country name, whether it is head office, list of swift codes, and SWIFT code.
 * Inherits everything from SwiftBranch except the country name and list of swift codes that are under HQ.
 *
 * The {@JsonPropertyOrder} annotation specifies the order in which fields are serialized
 * to JSON
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
     * Country name.
     */
    private String countryName;

    /*
     * List of swift codes of units subordinate to HQ.
     */
    private List<SwiftBranch> branches;

    /*
     * Returns the country name.
     *
     * @return countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /*
     * Sets the country name.
     *
     * @param countryName new name
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /*
     * Returns the subordinate banks from the list
     *
     * @return list of swift banks
     */
    public List<SwiftBranch> getBranches() {
        return branches;
    }

    /*
     * Sets the banks subject to the list
     *
     * @param swiftCodes list item
     */
    public void setBranches(List<SwiftBranch> branches) {
        this.branches = branches;
    }
}

