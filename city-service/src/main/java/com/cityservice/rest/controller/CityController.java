package com.cityservice.rest.controller;

import com.cityservice.model.Role;
import com.cityservice.rest.dto.CityDto;
import com.cityservice.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping
    public Page<CityDto> findCities(
            @RequestParam(value = "unique", required = false) Boolean uniqueOnly,
            @RequestParam(value = "countryName", required = false) String countryName,
            @RequestParam(value = "logo", required = false) String logo,
            @RequestParam(value = "name", required = false) String name,
            @PageableDefault(size = 50) Pageable pageable){
        return cityService.findCities(pageable);
    }

    @PreAuthorize("hasRole('EDITOR')")
    @PutMapping
    public ResponseEntity<Void> updateCity(@RequestBody CityDto cityDto){
        cityService.updateCity(cityDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/unique")
    public Page<CityDto> findUniqueCities(@PageableDefault(size = 50) Pageable pageable)
    {
        return cityService.findUniqueCitiesName(pageable);
    }

    @GetMapping(path ="/logo")
    public Page<CityDto> findCitiesByCountryName(
            @RequestParam(value = "countryName", required = false) String countryName,
            @PageableDefault(size = 50) Pageable pageable){
        return cityService.findCitiesByCountryName(pageable, countryName);
    }

    @GetMapping(path = "/logo")
    public Page<CityDto> findCitiesWithLogo(
            @PageableDefault(size = 50) Pageable pageable)
            {
        return cityService.findCitiesWithLogo(pageable);
    }

    @GetMapping(path = "/name")
    public Page<CityDto> findCitiesByName(
            @PageableDefault(size = 50) Pageable pageable,
            @RequestParam("name") String name) {
        return cityService.findCitiesByName(pageable, name);
    }


}
