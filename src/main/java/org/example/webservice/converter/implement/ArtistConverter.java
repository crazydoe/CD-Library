package org.example.webservice.converter.implement;

import org.example.webservice.converter.Converter;
import org.example.webservice.dto.ArtistDTO;
import org.example.webservice.entities.Artist;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ArtistConverter implements Converter<Artist, ArtistDTO> {
    private ModelMapper mapper = new ModelMapper();

    @Override
    public Artist toEntity(ArtistDTO dtoObject) {
        return mapper.map(dtoObject, Artist.class);
    }

    @Override
    public ArtistDTO toDTO(Artist entityObject) {
        return mapper.map(entityObject, ArtistDTO.class);
    }
}
