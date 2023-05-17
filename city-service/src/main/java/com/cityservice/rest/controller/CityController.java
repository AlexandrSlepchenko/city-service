package com.cityservice.rest.controller;

import com.cityservice.model.QCity;
import com.cityservice.rest.dto.CityDto;
import com.cityservice.service.CityService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPAExpressions;
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
public class CityController {

    private final CityService cityService;

    @GetMapping()
    public Page<CityDto> findCities(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "countryName", required = false) String countryName,
            @RequestParam(value = "logo", required = false) Boolean hasLogo,
            @RequestParam(value = "unique", required = false) Boolean isUnique,
            Pageable pageable) {

        QCity qCity = QCity.city;
        BooleanBuilder predicateBuilder = new BooleanBuilder();

        if (name != null) {
            predicateBuilder.and(qCity.name.containsIgnoreCase(name));
        }

        if (countryName != null) {
            predicateBuilder.and(qCity.country.name.equalsIgnoreCase(countryName));
        }

        if (hasLogo != null && hasLogo) {
            predicateBuilder.and(qCity.path.isNotNull());
        }

        if (isUnique != null && isUnique) {
            predicateBuilder.and(qCity.name.isNotNull()).and(qCity.name.notIn(
                    JPAExpressions.select(qCity.name)
                            .from(qCity)
                            .groupBy(qCity.name)
                            .having(qCity.name.count().gt(1))
            ));
        }

        Predicate predicate = predicateBuilder.getValue();

        return cityService.findCities(predicate, pageable);
    }

    @PreAuthorize("hasRole('EDITOR')")
    @PutMapping
    public CityDto updateCity(@RequestBody CityDto cityDto) {
        return cityService.updateCity(cityDto);
    }
}

//    @GetMapping(path = "/unique")
//    public Page<CityDto> findUniqueCities(@PageableDefault(size = 50) Pageable pageable)
//    {
//        return cityService.findUniqueCitiesName(pageable);
//    }
//
//    @GetMapping(path ="/countryName")
//    public Page<CityDto> findCitiesByCountryName(
//            @RequestParam(value = "countryName", required = false) String countryName,
//            @PageableDefault(size = 50) Pageable pageable){
//        return cityService.findCitiesByCountryName(countryName, pageable);
//    }
//
//    @GetMapping(path = "/logo")
//    public Page<CityDto> findCitiesWithLogo(
//            @PageableDefault(size = 50) Pageable pageable)
//            {
//        return cityService.findCitiesWithLogo(pageable);
//    }
//
//    @GetMapping(path = "/name")
//    public Page<CityDto> findCitiesByName(
//            @PageableDefault(size = 50) Pageable pageable,
//            @RequestParam("name") String name) {
//        return cityService.findCitiesByName(name, pageable);
//    }
//    @GetMapping
//    public Page<CityDto> findCities(
//            @RequestParam(value = "unique", required = false) Boolean uniqueOnly,
//            @RequestParam(value = "countryName", required = false) String countryName,
//            @RequestParam(value = "logo", required = false) String logo,
//            @RequestParam(value = "name", required = false) String name,
//            @PageableDefault(size = 50) Pageable pageable){
//        return cityService.findCities(pageable);
//    }
//}
