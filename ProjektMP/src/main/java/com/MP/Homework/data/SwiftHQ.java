package com.MP.Homework.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
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
    private String countryName;
    private List<SwiftBranch> branches;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<SwiftBranch> getBranches() {
        return branches;
    }

    public void setBranches(List<SwiftBranch> branches) {
        this.branches = branches;
    }
}

