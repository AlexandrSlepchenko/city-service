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

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

    @Mock
    private CityRepository cityRepository;

    @Mock
    private CityMapper cityMapper;

    @InjectMocks
    private CityServiceImpl cityService;

    @Test
    void findCities_ValidPredicateAndPageable_ReturnsPageOfCityDto() {
        Predicate predicate = mock(Predicate.class);
        Pageable pageable = mock(Pageable.class);
        List<City> cities = Collections.singletonList(new City());
        Page<City> cityPage = new PageImpl<>(cities);
        List<CityDto> cityDtos = Collections.singletonList(new CityDto());
        Page<CityDto> expectedPage = new PageImpl<>(cityDtos);

        when(cityRepository.findAll(any(Predicate.class), any(Pageable.class))).thenReturn(cityPage);
        when(cityMapper.toDto(any(City.class))).thenReturn(new CityDto());

        Page<CityDto> result = cityService.findCities(predicate, pageable);

        verify(cityRepository).findAll(any(Predicate.class), any(Pageable.class));
        verify(cityMapper, times(cities.size())).toDto(any(City.class));
        assertEquals(expectedPage, result);
    }

    @Test
    void updateCity_ValidCityDto_ReturnsUpdatedCityDto() {
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
    void updateCity_NullId_ThrowsWrongDataException() {
        CityDto cityDto = new CityDto();

        assertThrows(WrongDataException.class, () -> cityService.updateCity(cityDto));
        verify(cityMapper, never()).toEntity(any(CityDto.class));
        verify(cityRepository, never()).save(any(City.class));
        verify(cityMapper, never()).toDto(any(City.class));
    }
}

