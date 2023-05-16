package com.cityservice.mapper;

import com.cityservice.model.City;
import com.cityservice.rest.dto.CityDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        builder = @Builder(disableBuilder = true))
public interface CityMapper {

    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    @Mapping(target = "countryId", source = "city.country.id")
    CityDto cityToCityDTO(City city);

    @Mapping(target = "country", expression = "java(CountryMapper.INSTANCE.countryDTOToCountry(cityDTO))")
    City cityDTOToCity(CityDto cityDTO);

    @Mapping(target = "logo", source = "logo")
    CityDto cityToDto(City city);

    default Page<CityDto> mapPageToDto(Page<City> cityPage) {
        List<CityDto> cityDtos = cityPage.getContent()
                .stream()
                .map(this::cityToDto)
                .collect(Collectors.toList());

        return new PageImpl<>(cityDtos, cityPage.getPageable(), cityPage.getTotalElements());
    }
}
