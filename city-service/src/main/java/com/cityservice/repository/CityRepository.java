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
            select * from cities c 
            where c.name in (select name 
                            from cities 
                            group by name 
                            having COUNT(*) = 1)
                """, nativeQuery = true)
    Page<City> findUniqueCitiesName(Pageable pageable);

    @Query(value = """
            select * from cities ct
            join countries cn on ct.country = cn.id
            where cn =: countryName
                """, nativeQuery = true)
    Page<City> findCitiesByCountryName(Pageable pageable, String countryName);

    @Query(value = """
            select * from cities ct
            where logo is not null
                """, nativeQuery = true)
    Page<City> findCitiesWithLogo(Pageable pageable);

    @Query(value = """
        select * from cities c 
        where lower(c.name) like :name
        """, nativeQuery = true)
    Page<City> searchByName(Pageable pageable, String name);

    Page<City> getCities(Pageable pageable);
}
