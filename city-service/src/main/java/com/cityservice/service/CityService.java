package com.cityservice.service;

import com.cityservice.model.City;
import com.cityservice.rest.dto.CityDto;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityService {
//    Page<CityDto> findUniqueCitiesName(Pageable pageable);
//
//    Page<CityDto> findCitiesByCountryName(String countryName, Pageable pageable);
//
//    Page<CityDto> findCitiesWithLogo(Pageable pageable);
//
//    Page<CityDto> findCitiesByName(String name, Pageable pageable);

    Page<CityDto> findCities(Predicate predicate, Pageable pageable);

    CityDto updateCity(CityDto cityDto);
}
