package cityservice.com.src.main.controller;

import com.cityservice.rest.controller.CityController;
import com.cityservice.rest.dto.CityDto;
import com.cityservice.service.CityService;

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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityControllerTests {

    @Mock
    private CityService cityService;

    @InjectMocks
    private CityController cityController;

    @Test
    void findCities_WithParameters_ReturnsPageOfCityDto() {
        String name = "City";
        String countryName = "Country";
        Boolean hasLogo = true;
        Boolean isUnique = true;
        Pageable pageable = mock(Pageable.class);
        Page<CityDto> expectedPage = new PageImpl<>(List.of(new CityDto()));

        when(cityService.findCities(any(), any(), any(), any(), any())).thenReturn(expectedPage);

        Page<CityDto> result = cityController.findCities(name, countryName, hasLogo, isUnique, pageable);

        verify(cityService).findCities(any(), any(), any(), any(), any());
        assertEquals(expectedPage, result);
    }

    @Test
    void updateCity_ValidCityDto_ReturnsUpdatedCityDto() {
        CityDto cityDto = new CityDto();
        CityDto expectedCityDto = new CityDto();

        when(cityService.updateCity(any())).thenReturn(expectedCityDto);

        CityDto result = cityController.updateCity(cityDto);

        verify(cityService).updateCity(any());
        assertEquals(expectedCityDto, result);
    }
}

