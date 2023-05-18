package com.cityservice.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;


@ConstructorBinding
@ConfigurationProperties(prefix = "city-service")
@Getter
public class CityServiceProperties {

    private String username;
    private String password;

    public CityServiceProperties(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
