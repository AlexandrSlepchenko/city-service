package com.cityservice.repository;

import com.cityservice.model.City;
import com.cityservice.model.QCity;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

//    @Query(value = """
//            select * from cities c
//            where c.name in (select name
//                            from cities
//                            group by name
//                            having COUNT(*) = 1)
//                """, nativeQuery = true)
//    Page<City> findUniqueCitiesName(Pageable pageable);
//
//    @Query(value = """
//            select * from cities ct
//            join countries cn on ct.country = cn.id
//            where cn =: countryName
//                """, nativeQuery = true)
//    Page<City> findCitiesByCountryName(String countryName, Pageable pageable);
//
//    @Query(value = """
//            select * from cities ct
//            where logo is not null
//                """, nativeQuery = true)
//    Page<City> findCitiesWithLogo(Pageable pageable);
//
//    @Query(value = """
//        select * from cities c
//        where lower(c.name) like :name
//        """, nativeQuery = true)
//    Page<City> searchByName(String name, Pageable pageable);
//
//    @Query(value = """
//            select * from cities
//            """, nativeQuery = true)
//    Page<City> getCities(Pageable pageable);

//    @PersistenceContext
//    void setEntityManager(EntityManager entityManager);
//
//    default Page<City> findAll(Predicate predicate, Pageable pageable) {
//        JPAQuery<City> query = new JPAQuery<>(entityManager);
//        QCity qCity = QCity.city;
//        query.from(qCity).where(predicate);
//        return PageableExecutionUtils.getPage(query.fetch(), pageable, query::fetchCount);
//    }

    default Page<City> findAll(Predicate predicate, Pageable pageable) {
        return findAll(predicate, pageable);
    }
}
