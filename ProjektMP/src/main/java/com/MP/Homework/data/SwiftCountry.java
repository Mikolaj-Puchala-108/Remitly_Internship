package com.MP.Homework.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/*
 * Represents a country in the SWIFT system based on its ISO code.
 *
 * This class contains basic information about a country, such as ISO2,
 * the country name, and the swift codes in that country
 *
 * The {@JsonPropertyOrder} annotation specifies the order in which fields are serialized
 * to JSON..
 */
@JsonPropertyOrder({
        "countryISO2",
        "countryName",
        "swiftCodes"
})
public class SwiftCountry{

    /*
     * Country name.
     */
    private String countryName;

    /*
     * ISO2 country code.
     */
    private String countryISO2;

    /*
     * Swift code list
     */
    private List<SwiftBranch> swiftCodes;

    /*
     * Returns the country name.
     *
     * @return the country name
     */
    public String getCountryName() {
        return countryName;
    }

    /*
     * Sets the country name.
     *
     * @param countryName new country name
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /*
     * Returns ISO2 code
     *
     * @return ISO2 country code
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
     * Returns swift codes to a list
     *
     * The {@JsonProperty} annotation sets the json parameter name to swiftCodes\
     *
     * @return a list of swift codes
     */
    @JsonProperty("swiftCodes")
    public List<SwiftBranch> getBranches() {
        return swiftCodes;
    }

    /*
     * Sets swift codes from a list
     *
     * @param swiftCodes swift code list item
     */
    public void setSwiftCodes(List<SwiftBranch> swiftCodes) {
        this.swiftCodes = swiftCodes;
    }
}
