package org.example.webservice.converter.implement;

import org.example.webservice.converter.Converter;
import org.example.webservice.dto.TrackDTO;
import org.example.webservice.entities.Track;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TrackConverter implements Converter<Track, TrackDTO> {
    private ModelMapper mapper = new ModelMapper();

    @Override
    public Track toEntity(TrackDTO dtoObject) {
        return mapper.map(dtoObject, Track.class);
    }

    @Override
    public TrackDTO toDTO(Track entityObject) {
        return mapper.map(entityObject, TrackDTO.class);
    }

}
