package org.example.webservice.converter.implement;

import org.example.webservice.converter.Converter;
import org.example.webservice.dto.AlbumDTO;
import org.example.webservice.entities.Album;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AlbumConverter implements Converter<Album, AlbumDTO> {
    private ModelMapper mapper = new ModelMapper();

    @Override
    public Album toEntity(AlbumDTO dtoObject) {
        return mapper.map(dtoObject, Album.class);
    }

    @Override
    public AlbumDTO toDTO(Album entityObject) {
        return mapper.map(entityObject, AlbumDTO.class);
    }
}
