package com.cityservice.service;

import com.cityservice.rest.dto.CityDto;
import org.springframework.data.domain.Page;

public interface CityService {
    Page<CityDto> findUniqueCitiesName(int page, int size);

    Page<CityDto> findCitiesByCountryName(String countryName, int page, int size);
}
