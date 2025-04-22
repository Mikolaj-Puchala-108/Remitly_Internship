package com.MP.Homework.repo;
import com.MP.Homework.entity.SwiftCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * Repozytorium JPA dla encji {@link SwiftCode}.
 * Umożliwia wykonywanie operacji CRUD oraz zapytań opartych na konwencji Spring Data.
 */
@Repository
public interface SwiftCodeRepo extends JpaRepository<SwiftCode, String> {

    /*
     * Zwraca listę kodów SWIFT należących do danego kraju na podstawie kodu ISO2.
     *
     * @param countryISO2 dwuliterowy kod kraju (np. "PL", "DE")
     * @return lista dopasowanych rekordów SWIFT
     */
    List<SwiftCode> findByCountryISO2(String countryISO2);

    /*
     * Zwraca listę kodów SWIFT, które zaczynają się od podanego ciągu znaków.
     * Używane do znajdowania branchy banku na podstawie pierwszych 8 znaków kodu SWIFT centrali.
     *
     * @param headCode pierwsze 8 znaków kodu SWIFT (kod BIC bez branch code)
     * @return lista branchy pasujących do podanego prefiksu
     */
    List<SwiftCode> findBySwiftCodeStartingWith(String headCode);
}

