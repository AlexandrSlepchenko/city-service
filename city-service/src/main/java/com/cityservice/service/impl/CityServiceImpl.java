package com.cityservice.service.impl;

import com.cityservice.mapper.CityMapper;
import com.cityservice.repository.CityRepository;
import com.cityservice.rest.dto.CityDto;
import com.cityservice.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public Page<CityDto> findUniqueCitiesName(int page, int size) {
        return cityMapper.citiesToToDtos(cityRepository.findUniqueCitiesName(PageRequest.of(page, size)));
    }

    @Override
    public Page<CityDto> findCitiesByCountryName(String countryName, int page, int size) {
        return cityMapper.citiesToToDtos(cityRepository.findCitiesByCountryName(countryName, PageRequest.of(page, size)));
    }
}
