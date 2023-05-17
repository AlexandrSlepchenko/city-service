package com.cityservice.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "city-service")
@Getter
public class CityServiceProperties {

    private final String username;
    private final String password;

    @ConstructorBinding
    public CityServiceProperties(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
