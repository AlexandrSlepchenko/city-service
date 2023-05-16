package com.cityservice.rest.controller;

import com.cityservice.rest.dto.CityDto;
import com.cityservice.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/city", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping(path = "/unique")
    public Page<CityDto> findUniqueCities(@PageableDefault(size = 50) Pageable pageable)
    {
        return cityService.findUniqueCitiesName(pageable);
    }

    @GetMapping(path = "/county")
    public Page<CityDto> findCitiesByCountryName(
            @PageableDefault(size = 50) Pageable pageable,
            @PathVariable("name") String countryName) {
        return cityService.findCitiesByCountryName(pageable, countryName);
    }
}
