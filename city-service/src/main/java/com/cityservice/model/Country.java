package com.cityservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Country {
    private Long id;
    private String name;
    private List<Country> countries;
}
