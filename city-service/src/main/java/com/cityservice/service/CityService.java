package com.cityservice.service;

import com.cityservice.rest.dto.CityDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityService {

    Page<CityDto> findCities(String name, String countryName, Boolean hasLogo, Boolean isUnique,  Pageable pageable);

    CityDto updateCity(CityDto cityDto);
}
