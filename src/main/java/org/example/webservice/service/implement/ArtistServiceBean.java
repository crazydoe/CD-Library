package org.example.webservice.service.implement;

import org.example.webservice.dto.ArtistDTO;
import org.example.webservice.converter.implement.ArtistConverter; // review ???????
import org.example.webservice.converter.Converter;
import org.example.webservice.entities.Album;
import org.example.webservice.entities.Artist;
import org.example.webservice.repository.AlbumRepository;
import org.example.webservice.repository.ArtistRepository;
import org.example.webservice.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class ArtistServiceBean implements ArtistService {

    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;
    private final Converter<Artist, ArtistDTO> converter;

    @Autowired
    public ArtistServiceBean(final ArtistRepository artistRepository, final AlbumRepository albumRepository,
                             final ArtistConverter converter) {
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
        this.converter = converter;
    }

    @Override
    public Set<ArtistDTO> findAll() {
        Set<Artist> artists = artistRepository.findAll();
        return artists.stream().map(converter::toDTO).collect(Collectors.toSet());
    }

    @Override
    public ArtistDTO create(final ArtistDTO newArtist) {
        Artist artist = artistRepository.findOneByName(newArtist.getName())
                .orElseGet(() -> artistRepository.save(converter.toEntity(newArtist)));
        return converter.toDTO(artist);
    }

    @Override
    public Optional<ArtistDTO> findOne(final Long id) {
        return Optional.ofNullable(converter.toDTO(artistRepository.findOne(id)));
    }

    @Override
    public Optional<ArtistDTO> findByName(final String name) {
        return artistRepository.findOneByName(name).map(converter::toDTO);
    }

    @Override
    public ArtistDTO updateArtist(final ArtistDTO artistDTO) {
        return converter.toDTO(artistRepository.save(converter.toEntity(artistDTO)));
    }

    @Override
    public Boolean deleteArtist(final Long id) {
        Artist entity = artistRepository.findOne(id);
        if (entity == null) {
            return false;
        } else {
            artistRepository.delete(entity);
            return true;
        }
    }

    @Override
    public Boolean checkIfExist(final ArtistDTO artistDTO) {
        return artistRepository.exists(artistDTO.getId());
    }
}