package org.example.webservice.service;

import org.example.webservice.dto.GenreDTO;

import java.util.Optional;
import java.util.Set;

public interface GenreService {

    Set<GenreDTO> findAll();

    GenreDTO createGenre(GenreDTO genreDTO);

    Optional<GenreDTO> createForAlbum(GenreDTO newGenre, Long albumId);

    GenreDTO findById(Long id);

    Optional<GenreDTO> findByName(String name);

    GenreDTO updateGenre(GenreDTO genreDTO);

    Boolean deleteGenre(Long id);

    Boolean checkIfExist(GenreDTO genreDTO);
}
