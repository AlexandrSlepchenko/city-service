package com.cityservice.mapper;

import com.cityservice.model.Country;
import com.cityservice.rest.dto.CountryDto;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface CountryMapper {
    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);

    @Mapping(target = "id", source = "country.id")
    CountryDto countryToCountryDto(Country country);

    @Mapping(target = "id", ignore = true)
    Country countryDtoToCountry(CountryDto countryDTO);
}
