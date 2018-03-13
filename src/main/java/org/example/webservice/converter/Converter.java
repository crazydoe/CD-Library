package org.example.webservice.converter;

public interface Converter<ENTITY, DTO> {

    ENTITY toEntity(DTO dtoObject);

    DTO toDTO(ENTITY entityObject);

}


