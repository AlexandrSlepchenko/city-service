package com.cityservice.service;

import com.cityservice.rest.dto.CityDto;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityService {

    Page<CityDto> findCities(Predicate predicate, Pageable pageable);

    CityDto updateCity(CityDto cityDto);
}
