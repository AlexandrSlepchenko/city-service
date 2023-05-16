package com.cityservice.service;

import com.cityservice.rest.dto.CityDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityService {
    Page<CityDto> findUniqueCitiesName(Pageable pageable);

    Page<CityDto> findCitiesByCountryName(Pageable pageable, String countryName);
}
