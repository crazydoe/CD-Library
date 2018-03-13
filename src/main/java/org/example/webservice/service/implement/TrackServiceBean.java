package org.example.webservice.service.implement;

import org.example.webservice.dto.TrackDTO;
import org.example.webservice.converter.Converter;
import org.example.webservice.converter.implement.TrackConverter;
import org.example.webservice.entities.Track;
import org.example.webservice.repository.TrackRepository;
import org.example.webservice.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TrackServiceBean implements TrackService {

    private final TrackRepository trackRepository;
    private final Converter<Track, TrackDTO> converter;

    @Autowired
    public TrackServiceBean(final TrackRepository trackRepository, final TrackConverter converter) {
        this.trackRepository = trackRepository;
        this.converter = converter;
    }

    @Override
    public TrackDTO create(final TrackDTO trackDTO) {
        return converter.toDTO(trackRepository.save(converter.toEntity(trackDTO)));
    }

    @Override
    public Set<TrackDTO> create(final Set<TrackDTO> tracks) {
        List<Track> entities = tracks.stream().map(converter::toEntity).collect(Collectors.toList());
        List<Track> saved = trackRepository.save(entities);
        return saved.stream().map(converter::toDTO).collect(Collectors.toSet());
    }

    @Override
    public Optional<TrackDTO> findById(final Long id) {
        return Optional.ofNullable(trackRepository.findOne(id)).map(converter::toDTO);
    }

    @Override
    public TrackDTO updateTrack(final TrackDTO trackDTO) {
        return converter.toDTO(trackRepository.save(converter.toEntity(trackDTO)));
    }

    @Override
    public Boolean deleteTrack(final Long id) {
        Track entity = trackRepository.findOne(id);
        if(entity == null) {
            return false;
        } else {
            trackRepository.delete(entity);
            return true;
        }
    }

    @Override
    public Boolean checkIfExist(final TrackDTO trackDTO) {
        return trackRepository.exists(trackDTO.getId());
    }

}
