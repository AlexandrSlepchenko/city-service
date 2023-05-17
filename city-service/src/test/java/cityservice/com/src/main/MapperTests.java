package cityservice.com.src.main;

import com.cityservice.mapper.CityMapper;
import com.cityservice.model.City;
import com.cityservice.model.CityName;
import com.cityservice.model.Country;
import com.cityservice.rest.dto.CityDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class MapperTests {

    @InjectMocks
    private CityMapper cityMapper = Mappers.getMapper(CityMapper.class);

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testToDto() {
        City city = new City();
        city.setId(1L);
        CityName cityName = new CityName();
        cityName.setName("City Name");
        city.setName(cityName);
        Country country = new Country();
        country.setId(1L);
        city.setCountry(country);
        city.setPath("path/to/logo.png");

        CityDto cityDto = cityMapper.toDto(city);

        Assertions.assertEquals(1L, cityDto.getId());
        Assertions.assertEquals("City Name", cityDto.getName());
        Assertions.assertEquals(1L, cityDto.getCountryId());
        Assertions.assertEquals("path/to/logo.png", cityDto.getPath());
    }

    @Test
    public void testToEntity() {
        CityDto cityDto = new CityDto();
        cityDto.setId(1L);
        cityDto.setName("City Name");
        cityDto.setCountryId(1L);
        cityDto.setPath("path/to/logo.png");

        City city = cityMapper.toEntity(cityDto);

        Assertions.assertEquals(1L, city.getId());
        Assertions.assertNotNull(city.getName());
        Assertions.assertEquals("City Name", city.getName().getName());
        Assertions.assertNotNull(city.getCountry());
        Assertions.assertEquals(1L, city.getCountry().getId());
        Assertions.assertEquals("path/to/logo.png", city.getPath());
    }

    @Test
    void testMapPageToDto() {
        List<City> cities = Arrays.asList(
                createCity(1L, "City 1", 1L, "path1"),
                createCity(2L, "City 2", 2L, "path2"),
                createCity(3L, "City 3", 1L, "path3")
        );

        Page<City> cityPage = new PageImpl<>(cities);

        Page<CityDto> cityDtoPage = cityMapper.mapPageToDto(cityPage);

        Assertions.assertEquals(cities.size(), cityDtoPage.getContent().size());
        Assertions.assertEquals(cities.size(), cityDtoPage.getTotalElements());

        CityDto cityDto = cityDtoPage.getContent().get(0);
        Assertions.assertEquals(1L, cityDto.getId());
        Assertions.assertEquals("City 1", cityDto.getName());
        Assertions.assertEquals(1L, cityDto.getCountryId());
        Assertions.assertEquals("path1", cityDto.getPath());
    }

    private City createCity(Long id, String name, Long countryId, String path) {
        City city = new City();
        city.setId(id);
        CityName cityName = new CityName();
        cityName.setName(name);
        city.setName(cityName);
        Country country = new Country();
        country.setId(countryId);
        city.setCountry(country);
        city.setPath(path);
        return city;
    }
}
