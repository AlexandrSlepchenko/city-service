package cityservice.com.src.main;

import com.cityservice.mapper.CityMapper;
import com.cityservice.model.City;
import com.cityservice.model.Country;
import com.cityservice.rest.dto.CityDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class MapperTests {

    @Mock
    private City city;

    @InjectMocks
    private CityMapper cityMapper = Mappers.getMapper(CityMapper.class);

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCityToCityDto() {
        City city = new City();
        city.setId(1L);
        city.setName("City Name");
        Country country = new Country();
        country.setId(1L);
        city.setCountry(country);
        city.setPath("city_logo.png");

        CityDto cityDto = cityMapper.cityToCityDto(city);

        Assertions.assertEquals(1L, cityDto.getId());
        Assertions.assertEquals("City Name", cityDto.getName());
        Assertions.assertEquals(1L, cityDto.getCountryId());
        Assertions.assertEquals("city_logo.png", cityDto.getPath());
    }

    @Test
    void testCityDtoToCity() {
        CityDto cityDto = new CityDto();
        cityDto.setId(1L);
        cityDto.setName("City Name");
        cityDto.setCountryId(1L);
        cityDto.setPath("city_logo.png");

        City city = cityMapper.cityDtoToCity(cityDto);

        Assertions.assertEquals(1L, city.getId());
        Assertions.assertEquals("City Name", city.getName());
        Assertions.assertNotNull(city.getCountry());
        Assertions.assertEquals(1L, city.getCountry().getId());
        Assertions.assertEquals("city_logo.png", city.getPath());
    }

    @Test
    void testMapPageToDto() {
        List<City> cities = Arrays.asList(city, city, city);

        Page<City> cityPage = new PageImpl<>(cities, PageRequest.of(1,1), cities.size());

        Mockito.when(city.getId()).thenReturn(1L);
        Mockito.when(city.getName()).thenReturn("City Name");
        Mockito.when(city.getPath()).thenReturn("/path/to/logo.png");

        Page<CityDto> cityDtoPage = cityMapper.mapPageToDto(cityPage);

        Assertions.assertEquals(cities.size(), cityDtoPage.getContent().size());
        Assertions.assertEquals(cities.size() , cityDtoPage.getTotalElements());

        CityDto cityDto = cityDtoPage.getContent().get(0);
        Assertions.assertEquals(1L, cityDto.getId());
        Assertions.assertEquals("City Name", cityDto.getName());
        Assertions.assertEquals("/path/to/logo.png", cityDto.getPath());
    }
}
