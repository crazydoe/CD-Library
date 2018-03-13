package org.example.webservice.service;

import org.example.webservice.dto.TrackDTO;

import java.util.Optional;
import java.util.Set;

public interface TrackService {

    TrackDTO create(TrackDTO trackDTO);

    Set<TrackDTO> create(Set<TrackDTO> tracks);

    Optional<TrackDTO> findById(Long id);

    TrackDTO updateTrack(TrackDTO trackDTO);

    Boolean deleteTrack(Long id);

    Boolean checkIfExist(TrackDTO trackDTO);
}
