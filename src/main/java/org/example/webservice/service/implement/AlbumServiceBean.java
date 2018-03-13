package org.example.webservice.service.implement;

import org.example.webservice.converter.Converter;
import org.example.webservice.converter.implement.AlbumConverter;
import org.example.webservice.dto.AlbumDTO;
import org.example.webservice.entities.Album;
import org.example.webservice.repository.AlbumRepository;
import org.example.webservice.repository.TrackRepository;
import org.example.webservice.repository.specifications.AlbumSpecificationBuilder;
import org.example.webservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional
public class AlbumServiceBean implements AlbumService {

    private final AlbumRepository albumRepository;
    private final TrackRepository trackRepository;
    private final BandService bandService;
    private final GenreService genreService;
    private final ArtistService artistService;
    private final TrackService trackService;
    private final Converter<Album, AlbumDTO> converter;

    @Autowired
    public AlbumServiceBean(AlbumRepository albumRepository, TrackRepository trackRepository, BandService bandService,
                            GenreService genreService, ArtistService artistService, TrackService trackService,
                            AlbumConverter converter) {
        this.albumRepository = albumRepository;
        this.bandService = bandService;
        this.genreService = genreService;
        this.artistService = artistService;
        this.trackService = trackService;
        this.trackRepository = trackRepository;
        this.converter = converter;
    }

    @Override
    public Set<AlbumDTO> findAll() {

        Set<Album> albums = albumRepository.findAll();
        return albums.stream()
                .map(converter::toDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<AlbumDTO> findOne(final Long id) {
        Album album = albumRepository.findOne(id);
        return Optional.ofNullable(converter.toDTO(album));
    }

    @Override
    public Optional<AlbumDTO> create(final AlbumDTO newAlbum) {
//        Album albumEntity = converter.toEntity(newAlbum);

//        newAlbum.getArtists().forEach(artistService::create);
//        newAlbum.getGenres().forEach(genreService::createGenre);
//        newAlbum.getTracks().forEach(trackService::create);
//        bandService.createBand(newAlbum.getBand());
        return Optional.ofNullable(albumRepository.save(converter.toEntity(newAlbum))).map(converter::toDTO);

        //        return
//
//     newAlbum.getArtists().forEach(artist -> artistService.createForAlbum(artist, entityId)
//                .map(artistDTO -> saved.getArtists().add(artistDTO)));
//
//        newAlbum.getGenres().forEach(genre -> genreService.createForAlbum(genre, entityId)
//                .map(genreDTO -> saved.getGenres().add(genreDTO)));

//        return Optional.ofNullable(albumRepository.save(converter.toEntity(saved))).map(converter::toDTO);
    }

    @Override
    @Transactional
    public Optional<AlbumDTO> update(final AlbumDTO toUpdate) {
        return Optional.ofNullable(converter.toDTO(albumRepository.save(converter.toEntity(toUpdate))));
    }

    @Override
    public Set<AlbumDTO> find(final String artistName, final String bandName,
                              final String genreName, final String title) {

        Optional<Specification<Album>> specification = new AlbumSpecificationBuilder()
                .withArtist(artistName)
                .withBand(bandName)
                .withGenre(genreName)
                .withTitle(title)
                .build();

        return specification.map(albumRepository::findAll)
                .orElseGet(albumRepository::findAll)
                .stream().map(converter::toDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public Boolean delete(final Long albumId) {
        Album entity = albumRepository.findOne(albumId);
        if (entity != null) {
            trackRepository.delete(entity.getTracks());
            albumRepository.delete(entity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean exist(final Long albumId) {
        return albumRepository.exists(albumId);
    }

}
