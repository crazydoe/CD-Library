package org.example.webservice.service.implement;

import org.example.webservice.converter.Converter;
import org.example.webservice.converter.implement.GenreConverter;
import org.example.webservice.dto.GenreDTO;
import org.example.webservice.entities.Album;
import org.example.webservice.entities.Genre;
import org.example.webservice.repository.AlbumRepository;
import org.example.webservice.repository.GenreRepository;
import org.example.webservice.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GenreServiceBean implements GenreService {

    private final GenreRepository genreRepository;
    private final Converter<Genre, GenreDTO> converter;
    private AlbumRepository albumRepository;

    @Autowired
    public GenreServiceBean(final GenreRepository genreRepository, final AlbumRepository albumRepository,
                            final GenreConverter converter) {
        this.genreRepository = genreRepository;
        this.albumRepository = albumRepository;
        this.converter = converter;
    }

    @Override
    public Set<GenreDTO> findAll() {
        Set<Genre> artists = genreRepository.findAll();
        return artists.stream()
                .map(converter::toDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public GenreDTO createGenre(final GenreDTO genreDTO) {
        Optional<GenreDTO> entity = findByName(genreDTO.getName());
        return entity.orElseGet(() -> converter.toDTO(genreRepository.save(converter.toEntity(genreDTO))));
    }

    @Override
    public Optional<GenreDTO> createForAlbum(final GenreDTO newGenre, final Long albumId) {
        Optional<Album> found = albumRepository.findOneById(albumId);
        Optional<Genre> genre = found.map(album -> {
            Genre savedGenre = genreRepository.findOneByName(newGenre.getName())
                    .orElseGet(() -> genreRepository.save(converter.toEntity(newGenre)));
            album.addGenre(savedGenre);
            albumRepository.save(album);
            return savedGenre;
        });
        return genre.map(converter::toDTO);
    }

    @Override
    public GenreDTO findById(final Long id) {
        return converter.toDTO(genreRepository.findOne(id));
    }

    @Override
    public Optional<GenreDTO> findByName(final String name) {
        return genreRepository.findOneByName(name).map(converter::toDTO);
    }

    @Override
    public GenreDTO updateGenre(final GenreDTO genreDTO) {
        return converter.toDTO(genreRepository.save(converter.toEntity(genreDTO)));
    }

    @Override
    public Boolean deleteGenre(final Long id) {
        Genre entity = genreRepository.findOne(id);
        if (entity == null) {
            return false;
        } else {
            genreRepository.delete(entity);
            return true;
        }
    }

    @Override
    public Boolean checkIfExist(final GenreDTO genreDTO) {
        return genreRepository.exists(genreDTO.getId());
    }

}
