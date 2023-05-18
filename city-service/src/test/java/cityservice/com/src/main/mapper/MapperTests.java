package cityservice.com.src.main.mapper;

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
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

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

        CityDto cityDto = cityMapper.toDto(city);

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

        City city = cityMapper.toEntity(cityDto);

        Assertions.assertEquals(1L, city.getId());
        Assertions.assertEquals("City Name", city.getName());
        Assertions.assertNotNull(city.getCountry());
        Assertions.assertEquals(1L, city.getCountry().getId());
        Assertions.assertEquals("city_logo.png", city.getPath());
    }
}
