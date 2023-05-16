package com.cityservice.service.impl;

import com.cityservice.mapper.CityMapper;
import com.cityservice.repository.CityRepository;
import com.cityservice.rest.dto.CityDto;
import com.cityservice.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public Page<CityDto> findUniqueCitiesName(Pageable pageable) {
        return cityMapper.mapPageToDto(cityRepository.findUniqueCitiesName(pageable));
    }

    @Override
    public Page<CityDto> findCitiesByCountryName(Pageable pageable, String countryName) {
        return cityMapper.mapPageToDto(cityRepository.findCitiesByCountryName(pageable, countryName));
    }
}
