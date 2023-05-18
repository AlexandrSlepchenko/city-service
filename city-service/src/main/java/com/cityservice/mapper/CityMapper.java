package com.cityservice.mapper;

import com.cityservice.model.City;
import com.cityservice.rest.dto.CityDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        builder = @Builder(disableBuilder = true))
public interface CityMapper {

    @Mapping(source = "country.id", target = "countryId")
    CityDto toDto(City city);

    @Mapping(source = "countryId", target = "country.id")
    City toEntity(CityDto cityDto);
}
