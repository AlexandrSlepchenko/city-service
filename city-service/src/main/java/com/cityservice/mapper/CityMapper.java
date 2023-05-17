package com.cityservice.mapper;

import com.cityservice.model.City;
import com.cityservice.model.CityName;
import com.cityservice.rest.dto.CityDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        builder = @Builder(disableBuilder = true))
public interface CityMapper {

    @Mapping(source = "name.name", target = "name")
    @Mapping(source = "country.id", target = "countryId")
    CityDto toDto(City city);

    @Mapping(source = "name", target = "name.name")
    @Mapping(source = "countryId", target = "country.id")
    City toEntity(CityDto cityDto);

    default Page<CityDto> mapPageToDto(Page<City> cityPage) {
        return cityPage.map(this::toDto);
    }

    default CityName cityNameFromCity(City city) {
        if (city == null) {
            return null;
        }
        CityName cityName = new CityName();
        cityName.setName(city.getName().getName());
        return cityName;
    }

    default City cityNameToCity(CityName cityName) {
        if (cityName == null) {
            return null;
        }
        City city = new City();
        CityName cityN = new CityName();
        cityN.setName(cityName.getName());
        city.setName(cityN);
        return city;
    }

}
