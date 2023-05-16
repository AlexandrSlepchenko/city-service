package com.cityservice.repository;

import com.cityservice.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query(value = """
            select distinct name from cities
                """, nativeQuery = true)
    Page<City> findUniqueCitiesName(Pageable pageable);

    @Query(value = """
            select * from cities ct
            join countries cn on ct.country = cn.id
            where cn =: countryName
                """, nativeQuery = true)
    Page<City> findCitiesByCountryName(Pageable pageable, String countryName);
}
