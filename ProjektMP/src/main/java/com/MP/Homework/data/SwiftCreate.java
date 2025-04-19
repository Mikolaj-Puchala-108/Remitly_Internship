package com.MP.Homework.data;
import jakarta.validation.constraints.*;

public class SwiftCreate {

    @NotBlank(message = "Address cannot be empty")
    public String address;

    @NotBlank(message = "Bank name cannot be empty")
    public String bankName;

    @Pattern(regexp = "^[A-Z]{2}$", message = "The country code should consist of 2 capital letters")
    public String countryISO2;

    @NotBlank(message = "Country name cannot be empty")
    public String CountryName;

    public boolean isHQ;

    @Pattern(regexp = "^[A-Z0-9]{11}$", message = "SWIFT should be 11 characters long")
    private String swiftCode;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCountryISO2() {
        return countryISO2;
    }

    public void setCountryISO(String countryISO) {
        this.countryISO2 = countryISO;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public boolean getHQ() {
        return isHQ;
    }

    public void setHQ(boolean isHQ) {
        this.isHQ = isHQ;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }
}
