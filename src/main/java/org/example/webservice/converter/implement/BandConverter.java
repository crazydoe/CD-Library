package org.example.webservice.converter.implement;

import org.example.webservice.converter.Converter;
import org.example.webservice.dto.BandDTO;
import org.example.webservice.entities.Band;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BandConverter implements Converter<Band, BandDTO> {
    private ModelMapper mapper = new ModelMapper();

    @Override
    public Band toEntity(BandDTO dtoObject) {
        return mapper.map(dtoObject, Band.class);
    }

    @Override
    public BandDTO toDTO(Band entityObject) {
        return mapper.map(entityObject, BandDTO.class);
    }
}
