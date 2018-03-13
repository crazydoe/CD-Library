package org.example.webservice.service;

import org.example.webservice.dto.ArtistDTO;

import java.util.Optional;
import java.util.Set;

public interface ArtistService {

    Set<ArtistDTO> findAll();

    ArtistDTO create(ArtistDTO artistDTO);

    Optional<ArtistDTO> findOne(Long id);

    Optional<ArtistDTO> findByName(String name);

    ArtistDTO updateArtist(ArtistDTO artistDTO);

    Boolean deleteArtist(Long id);

    Boolean checkIfExist(ArtistDTO artistDTO);
}
