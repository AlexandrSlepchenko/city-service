package com.cityservice.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityDto {
    private Long id;
    private String name;
    private Long countryId;
    private String path;
}
