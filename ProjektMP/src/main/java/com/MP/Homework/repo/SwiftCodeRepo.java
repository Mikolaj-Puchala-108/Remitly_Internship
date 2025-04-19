package com.MP.Homework.repo;
import com.MP.Homework.entity.SwiftCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SwiftCodeRepo extends JpaRepository<SwiftCode, String> { //działa na Entity SwiftCode, id jest str
    List<SwiftCode> findByCountryISO2(String countryISO2);
    List<SwiftCode> findBySwiftCodeStartingWith(String headCode); //szukania branchy
}

