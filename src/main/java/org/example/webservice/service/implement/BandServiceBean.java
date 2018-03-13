package org.example.webservice.service.implement;

import org.example.webservice.converter.Converter;
import org.example.webservice.converter.implement.BandConverter;
import org.example.webservice.dto.BandDTO;
import org.example.webservice.entities.Band;
import org.example.webservice.repository.BandRepository;
import org.example.webservice.service.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BandServiceBean implements BandService {

    private final BandRepository bandRepository;
    private final Converter<Band, BandDTO> converter;

    @Autowired
    public BandServiceBean(final BandRepository bandRepository, final BandConverter converter) {
        this.bandRepository = bandRepository;
        this.converter = converter;
    }

    @Override
    public Set<BandDTO> findAll() {
        Set<Band> artists = bandRepository.findAll();
        return artists.stream().map(converter::toDTO).collect(Collectors.toSet());
    }

    @Override
    public BandDTO createBand(final BandDTO bandDTO) {
        Optional<BandDTO> band = findByName(bandDTO.getName());
        return band.orElseGet(() -> converter.toDTO(bandRepository.save(converter.toEntity(bandDTO))));
    }

    @Override
    public Optional<BandDTO> findById(final Long id) {
        return Optional.ofNullable(bandRepository.findOne(id)).map(converter::toDTO);
    }

    @Override
    public Optional<BandDTO> findByName(final String name) {
        return bandRepository.findOneByName(name).map(converter::toDTO);
    }

    @Override
    public BandDTO updateBand(final BandDTO bandDTO) {
        return converter.toDTO(bandRepository.save(converter.toEntity(bandDTO)));
    }

    @Override
    public Boolean deleteBand(final Long id) {
        Band entity = bandRepository.findOne(id);
        if (entity == null) {
            return false;
        } else {
            bandRepository.delete(entity);
            return true;
        }
    }

    @Override
    public Boolean checkIfExist(final BandDTO bandDTO) {
        return bandRepository.exists(bandDTO.getId());
    }

}
