package com.MP.Homework.data;
import jakarta.validation.constraints.*;

/*
 * Reprezentuje dane wejściowe wymagane do utworzenia nowego oddziału bankowego w systemie SWIFT.
 *
 * Klasa zawiera adnotacje walidacyjne z biblioteki Jakarta Bean Validation
 * (np. @NotBlank, @Pattern), które służą do weryfikacji poprawności danych przesyłanych
 * przez użytkownika np. w formularzach lub zapytaniach REST.
 */
public class SwiftCreate {

    /*
     * Adres oddziału banku.
     * Nie może być pusty.
     */
    @NotBlank(message = "Address cannot be empty")
    public String address;

    /*
     * Nazwa banku.
     * Nie może być pusta.
     */
    @NotBlank(message = "Bank name cannot be empty")
    public String bankName;

    /*
     * Kod kraju w formacie ISO 3166-1 alpha-2.
     * Musi składać się z 2 wielkich liter (np. "PL").
     */
    @Pattern(regexp = "^[A-Z]{2}$", message = "The country code should consist of 2 capital letters")
    public String countryISO2;

    /*
     * Pełna nazwa kraju.
     * Nie może być pusta.
     */
    @NotBlank(message = "Country name cannot be empty")
    public String CountryName;

    /*
     * Flaga określająca, czy oddział jest centralą banku. Używana podczas tworzenia nowego banku
     */
    public boolean isHQ;

    /*
     * Kod SWIFT.
     * Musi mieć dokładnie 11 znaków składających się z wielkich liter i/lub cyfr.
     */
    @Pattern(regexp = "^[A-Z0-9]{11}$", message = "SWIFT should be 11 characters long")
    private String swiftCode;

    // Gettery i settery

    /*
     * Zwraca adres oddziału.
     *
     * @return adres
     */
    public String getAddress() {
        return address;
    }

    /*
     * Ustawia adres oddziału.
     *
     * @param address adres oddziału
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /*
     * Zwraca nazwę banku.
     *
     * @return nazwa banku
     */
    public String getBankName() {
        return bankName;
    }

    /*
     * Ustawia nazwę banku.
     *
     * @param bankName nazwa banku
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /*
     * Zwraca kod kraju w formacie ISO.
     *
     * @return kod kraju ISO2
     */
    public String getCountryISO2() {
        return countryISO2;
    }

    /*
     * Ustawia kod kraju w formacie ISO.
     *
     * @param countryISO kod kraju
     */
    public void setCountryISO(String countryISO) {
        this.countryISO2 = countryISO;
    }

    /*
     * Zwraca nazwę kraju.
     *
     * @return nazwa kraju
     */
    public String getCountryName() {
        return CountryName;
    }

    /*
     * Ustawia nazwę kraju.
     *
     * @param countryName nazwa kraju
     */
    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    /*
     * Zwraca informację, czy oddział jest centralą.
     *
     * @return {@code true} jeśli to centrala
     */
    public boolean getHQ() {
        return isHQ;
    }

    /*
     * Ustawia informację, czy oddział jest centralą.
     *
     * @param isHQ {@code true} jeśli to centrala
     */
    public void setHQ(boolean isHQ) {
        this.isHQ = isHQ;
    }

    /*
     * Zwraca kod SWIFT.
     *
     * @return kod SWIFT
     */
    public String getSwiftCode() {
        return swiftCode;
    }

    /*
     * Ustawia kod SWIFT.
     *
     * @param swiftCode kod SWIFT
     */
    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }
}
