package com.cityservice.rest.controller;

import com.cityservice.rest.dto.CityDto;
import com.cityservice.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor

public class CityController{

    private final CityService cityService;

    @GetMapping()
    public Page<CityDto> findCities(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "countryName", required = false) String countryName,
            @RequestParam(value = "logo", required = false) Boolean hasLogo,
            @RequestParam(value = "unique", required = false) Boolean isUnique,
            Pageable pageable) {
        return cityService.findCities(name, countryName , hasLogo, isUnique, pageable);
    }

    @PreAuthorize("hasRole('EDITOR')")
    @PutMapping
    public CityDto updateCity(@RequestBody CityDto cityDto) {
        return cityService.updateCity(cityDto);
    }
}
