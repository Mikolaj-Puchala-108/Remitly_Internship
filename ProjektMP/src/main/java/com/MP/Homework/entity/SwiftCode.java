package com.MP.Homework.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "banks")
public class SwiftCode {


    @Column(name = "`COUNTRY ISO2 CODE`")
    private String countryISO2;
    @Id
    @Column(name = "`SWIFT CODE`")
    private String swiftCode;
    private String name;
    private String address;
    @Column(name = "`TOWN NAME`")
    private String townName;
    @Column(name = "`COUNTRY NAME`")
    private String countryName;
    @Column(name = "`TIME ZONE`")
    private String timeZone;

    private boolean isHQ;

    public SwiftCode() {}

    public SwiftCode(String countryISO2, String SwiftCode, String Name, String Address, String TownName, String CountryName, String TimeZone) {
        this.countryISO2 = countryISO2;
        this.swiftCode = SwiftCode;
        this.name = Name;
        this.address = Address;
        this.townName = TownName;
        this.countryName = CountryName;
        this.timeZone = TimeZone;
    }




    @JsonProperty("countryISO2")
    public String getCountryISO2() {
        return countryISO2;
    }

    public void setCountryISO2(String countryISO2) {
        this.countryISO2 = countryISO2;
    }

    @JsonProperty("swiftCode")
    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    @JsonProperty("bankName")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("townName")
    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    @JsonProperty("countryName")
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @JsonProperty("timeZone")
    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public boolean isHQ() {
        return isHQ;
    }

    public void setHQ(boolean HQ) {
        isHQ = HQ;
    }

    @JsonProperty("isHeadquarter")
    public boolean isHeadquarter() {
        return swiftCode != null && (swiftCode.toUpperCase().endsWith("XXX"));
    }

}