package org.example.webservice.service;

import org.example.webservice.dto.AlbumDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.Set;

public interface AlbumService {

    Set<AlbumDTO> findAll();

    Optional<AlbumDTO> findOne(Long id);

    Optional<AlbumDTO> create(AlbumDTO newAlbum);

    Optional<AlbumDTO> update(AlbumDTO toUpdate);

    Set<AlbumDTO> find(String artistName, String bandName,
                       String genreName, String title);

    Boolean delete(Long albumId);

    Boolean exist(Long albumId);
}
