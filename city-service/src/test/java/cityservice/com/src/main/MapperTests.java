package cityservice.com.src.main;

import com.cityservice.mapper.CityMapper;
import com.cityservice.mapper.CountryMapper;
import com.cityservice.model.City;
import com.cityservice.model.Country;
import com.cityservice.rest.dto.CityDto;
import com.cityservice.rest.dto.CountryDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.awt.print.Pageable;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MapperTests {

    @Mock
    private Country country;

    @Mock
    private City city;

    @InjectMocks
    private CountryMapper countryMapper = CountryMapper.INSTANCE;

    @InjectMocks
    private CityMapper cityMapper = CityMapper.INSTANCE;

    @Test
    public void testCountryToCountryDto() {
        when(country.getId()).thenReturn(1L);
        when(country.getName()).thenReturn("Country Name");

        CountryDto countryDto = countryMapper.countryToCountryDto(country);

        Assertions.assertEquals(1L, countryDto.getId());
        Assertions.assertEquals("Country Name", countryDto.getName());
    }

    @Test
    public void testCountryDtoToCountry() {
        CountryDto countryDTO = new CountryDto();
        countryDTO.setId(1L);
        countryDTO.setName("Country Name");

        Country country = countryMapper.countryDtoToCountry(countryDTO);

        Assertions.assertEquals(1L, country.getId());
        Assertions.assertEquals("Country Name", country.getName());
    }

    @Test
    public void testCityToCityDto() {
        when(city.getId()).thenReturn(1L);
        when(city.getName()).thenReturn("City Name");
        when(city.getCountry()).thenReturn(country);

        CityDto cityDTO = cityMapper.cityToCityDTO(city);

        Assertions.assertEquals(1L, cityDTO.getId());
        Assertions.assertEquals("City Name", cityDTO.getName());
        Assertions.assertEquals(1L, cityDTO.getCountryId());
    }

    @Test
    public void testCityDTOToCity() {
        CityDto cityDto = new CityDto();
        cityDto.setId(1L);
        cityDto.setName("City Name");
        cityDto.setCountryId(1L);

        CountryDto countryDto = new CountryDto();
        countryDto.setId(1L);
        countryDto.setName("Country Name");

        when(countryMapper.countryDtoToCountry(countryDto)).thenReturn(country);

        City city = cityMapper.cityDTOToCity(cityDto);

        Assertions.assertEquals(1L, city.getId());
        Assertions.assertEquals("City Name", city.getName());
        Assertions.assertEquals(country, city.getCountry());
    }

    @Test
    public void testMapPageToDto() {
        List<City> cities = Arrays.asList(city, city, city);

        Pageable pageable = Mockito.mock(Pageable.class);

        Page<City> cityPage = new PageImpl<>(cities, (org.springframework.data.domain.Pageable) pageable, cities.size());

        Mockito.when(city.getId()).thenReturn(1L);
        Mockito.when(city.getName()).thenReturn("City Name");
        Mockito.when(city.getPath()).thenReturn("/path/to/logo.png");

        Page<CityDto> cityDtoPage = cityMapper.mapPageToDto(cityPage);

        Assertions.assertEquals(cities.size(), cityDtoPage.getContent().size());
        Assertions.assertEquals(cities.size(), cityDtoPage.getTotalElements());

        CityDto cityDto = cityDtoPage.getContent().get(0);
        Assertions.assertEquals(1L, cityDto.getId());
        Assertions.assertEquals("City Name", cityDto.getName());
        Assertions.assertEquals("/path/to/logo.png", cityDto.getLogo());
    }
}
