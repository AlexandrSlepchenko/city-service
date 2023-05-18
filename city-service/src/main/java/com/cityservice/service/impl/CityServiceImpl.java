package com.cityservice.service.impl;

import com.cityservice.exception.WrongDataException;
import com.cityservice.mapper.CityMapper;
import com.cityservice.model.City;
import com.cityservice.model.QCity;
import com.cityservice.repository.CityRepository;
import com.cityservice.rest.dto.CityDto;
import com.cityservice.service.CityService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPAExpressions;
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
    public Page<CityDto> findCities(String name, String countryName, Boolean hasLogo, Boolean isUnique,  Pageable pageable) {

        QCity qCity = QCity.city;
        BooleanBuilder predicateBuilder = new BooleanBuilder();

        if (name != null) {
            predicateBuilder.and(qCity.name.containsIgnoreCase(name));
        }

        if (countryName != null) {
            predicateBuilder.and(qCity.country.name.equalsIgnoreCase(countryName));
        }

        if (hasLogo != null && hasLogo) {
            predicateBuilder.and(qCity.path.isNotNull());
        }

        if (isUnique != null && isUnique) {
            predicateBuilder.and(qCity.name.isNotNull()).and(qCity.name.notIn(
                    JPAExpressions.select(qCity.name)
                            .from(qCity)
                            .groupBy(qCity.name)
                            .having(qCity.name.count().gt(1))
            ));
        }

        Predicate predicate = predicateBuilder.getValue();

        if (predicate == null){
            var cities = (cityRepository.findAll(pageable));
            return cities.map(cityMapper::toDto);
        }

        Page<City> cityPage = cityRepository.findAll(predicate, pageable);
        return cityPage.map(cityMapper::toDto);
    }

    @Override
    public CityDto updateCity(CityDto cityDto) {
        if (cityDto.getId() == null) {
            throw new WrongDataException("City must contain id");
        }
        var city = cityRepository.save(cityMapper.toEntity(cityDto));
        return cityMapper.toDto(city);
    }
}
