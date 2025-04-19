package com.MP.Homework.data;

import com.MP.Homework.entity.SwiftCode;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
@JsonPropertyOrder({
        "countryISO2",
        "countryName",
        "swiftCodes"
})
public class SwiftCountry{
    private String countryName;
    private String countryISO2;
    private List<SwiftBranch> swiftCodes;


    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryISO2() {
        return countryISO2;
    }

    public void setCountryISO2(String countryISO2) {
        this.countryISO2 = countryISO2;
    }

    @JsonProperty("swiftCodes")
    public List<SwiftBranch> getBranches() {
        return swiftCodes;
    }

    public void setSwiftCodes(List<SwiftBranch> swiftCodes) {
        this.swiftCodes = swiftCodes;
    }
}
