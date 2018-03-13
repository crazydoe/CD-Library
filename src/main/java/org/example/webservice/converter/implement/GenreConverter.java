package org.example.webservice.converter.implement;

import org.example.webservice.converter.Converter;
import org.example.webservice.dto.GenreDTO;
import org.example.webservice.entities.Genre;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GenreConverter implements Converter<Genre, GenreDTO> {
    private ModelMapper mapper = new ModelMapper();

    @Override
    public Genre toEntity(GenreDTO dtoObject) {
        return mapper.map(dtoObject, Genre.class);
    }

    @Override
    public GenreDTO toDTO(Genre entityObject) {
        return mapper.map(entityObject, GenreDTO.class);
    }
}
