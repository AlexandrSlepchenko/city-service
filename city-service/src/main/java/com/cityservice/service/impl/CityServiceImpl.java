package com.cityservice.service.impl;

import com.cityservice.exception.WrongDataException;
import com.cityservice.mapper.CityMapper;
import com.cityservice.model.City;
import com.cityservice.repository.CityRepository;
import com.cityservice.repository.CitySearchRepository;
import com.cityservice.rest.dto.CityDto;
import com.cityservice.service.CityService;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CityServiceImpl implements CityService {

    private final CitySearchRepository citySearchRepository;
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public Page<CityDto> findCities(Predicate predicate, Pageable pageable) {
        Page<City> cityPage = citySearchRepository.findAll(predicate, pageable);
        return cityPage.map(cityMapper::toDto);
    }

    @Override
    public CityDto updateCity(CityDto cityDto) {
        if (cityDto.getId() != null) {
            return cityMapper.toDto(cityRepository.save(cityMapper.toEntity(cityDto)));
        }
        throw new WrongDataException("Citi have to contains id");
    }
}
