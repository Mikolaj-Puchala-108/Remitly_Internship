package com.MP.Homework.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/*
 * Represents a bank branch in the SWIFT system if displayed solo.
 *
 * This class contains basic information about a bank branch, such as address,
 * bank name, country code, country name, whether it is headquartered, and SWIFT code.
 * It inherits everything from SwiftBranch except the country name.
 *
 * The {@JsonPropertyOrder} annotation specifies the order in which fields are serialized
 * to JSON.
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
     * Country name.
     */
    private  String countryName;

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
}
