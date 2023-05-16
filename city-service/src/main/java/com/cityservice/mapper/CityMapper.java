package com.cityservice.mapper;

import com.cityservice.model.City;
import com.cityservice.rest.dto.CityDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        builder = @Builder(disableBuilder = true))
public interface CityMapper extends BaseMapper<City, CityDto>{

    @Mapping(target = "logo", source = "logo.path") // Assuming the logo field in CityDto is a string representing the logo path
    CityDto cityToDto(City city);

    default Page<CityDto> citiesToToDtos(Page<City> cityPage) {
        List<CityDto> cityDtos = cityPage.getContent()
                .stream()
                .map(this::cityToDto)
                .collect(Collectors.toList());

        return new PageImpl<>(cityDtos, cityPage.getPageable(), cityPage.getTotalElements());
    }
}
