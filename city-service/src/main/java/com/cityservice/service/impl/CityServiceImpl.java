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

    @Override
    public Page<CityDto> findCitiesWithLogo(Pageable pageable) {
        return cityMapper.mapPageToDto(cityRepository.findCitiesWithLogo(pageable));
    }

    @Override
    public Page<CityDto> findCitiesByName(Pageable pageable, String name) {
        return cityMapper.mapPageToDto(cityRepository.searchByName(pageable, name + "%"));
    }

    @Override
    public Page<CityDto> findCities(Pageable pageable) {
        return cityMapper.mapPageToDto(cityRepository.getCities(pageable));
    }

    @Override
    public void updateCity(CityDto cityDto) {
        cityRepository.save(cityMapper.toEntity(cityDto));
    }


}
