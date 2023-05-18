package com.cityservice.repository;

import com.cityservice.model.City;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CitySearchRepository extends QuerydslPredicateExecutor<City> {
}
