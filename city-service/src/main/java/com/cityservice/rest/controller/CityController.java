package com.cityservice.rest.controller;

import com.cityservice.rest.dto.CityDto;
import com.cityservice.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/city", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping(path = "/unique")
    public ResponseEntity<Page<CityDto>> findUniqueCities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size)
    {
        return ResponseEntity.ok(cityService.findUniqueCitiesName(page, size));
    }

    @GetMapping(path = "/county")
    public ResponseEntity<Page<CityDto>> findCitiesByCountryName(
            @PathVariable("name") String countryName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size)
    {
        return ResponseEntity.ok(cityService.findUniqueCitiesName(page, size));
    }
}
