package cityservice.com.src.main.service;

import com.cityservice.exception.WrongDataException;
import com.cityservice.mapper.CityMapper;
import com.cityservice.model.City;
import com.cityservice.repository.CityRepository;
import com.cityservice.rest.dto.CityDto;
import com.cityservice.service.impl.CityServiceImpl;
import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

    @Mock
    private CityRepository cityRepository;

    @Mock
    private CityMapper cityMapper;

    @InjectMocks
    private CityServiceImpl cityService;

    @Test
    void findCitiesPredicateEmptyTest() {
        Pageable pageable = mock(Pageable.class);
        Page<City> cityPage = new PageImpl<>(List.of(new City()));
        Page<CityDto> cityDtoPage = new PageImpl<>(List.of(new CityDto()));

        when(cityRepository.findAll(any(Pageable.class))).thenReturn(cityPage);
        when(cityMapper.toDto(any(City.class))).thenReturn(new CityDto());

        Page<CityDto> result = cityService.findCities(null, null, null, null, pageable);

        assertEquals(cityDtoPage.getSize(), result.getSize());
    }

    @Test
    void findCitiesPredicateNotEmptyTest() {
        String name = "City";
        String countryName = "Country";
        Boolean hasLogo = true;
        Boolean isUnique = true;
        Pageable pageable = mock(Pageable.class);
        Page<CityDto> cityDtoPage = new PageImpl<>(List.of(new CityDto()));

        when(cityRepository.findAll(any(Predicate.class), any(Pageable.class))).thenReturn(new PageImpl<>(List.of(new City())));
        when(cityMapper.toDto(any(City.class))).thenReturn(new CityDto());

        Page<CityDto> result = cityService.findCities(name, countryName, hasLogo, isUnique, pageable);

        assertEquals(1, result.getSize());
        assertEquals(cityDtoPage.getSize(), result.getContent().size());
    }

    @Test
    void updateCityTest() {
        CityDto cityDto = new CityDto();
        cityDto.setId(1L);
        City city = new City();
        city.setId(1L);
        CityDto expectedCityDto = new CityDto();
        expectedCityDto.setId(1L);

        when(cityMapper.toEntity(any(CityDto.class))).thenReturn(city);
        when(cityRepository.save(any(City.class))).thenReturn(city);
        when(cityMapper.toDto(any(City.class))).thenReturn(expectedCityDto);

        CityDto result = cityService.updateCity(cityDto);

        verify(cityMapper).toEntity(any(CityDto.class));
        verify(cityRepository).save(any(City.class));
        verify(cityMapper).toDto(any(City.class));
        assertEquals(expectedCityDto, result);
    }

    @Test
    void updateCityNullIdTest() {
        CityDto cityDto = new CityDto();

        assertThrows(WrongDataException.class, () -> cityService.updateCity(cityDto));
        verify(cityMapper, never()).toEntity(any(CityDto.class));
        verify(cityRepository, never()).save(any(City.class));
        verify(cityMapper, never()).toDto(any(City.class));
    }
}

