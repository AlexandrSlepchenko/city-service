package com.cityservice.mapper;

public interface BaseMapper <T, D>{
    T toEntity(D dto);
    D toDTO(T entity);
}
