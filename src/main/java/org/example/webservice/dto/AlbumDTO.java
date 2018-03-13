package org.example.webservice.dto;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AlbumDTO {

    private Long id;
    @NotBlank
    private String title;
    @NotEmpty
    private BandDTO band;
    @NotBlank
    private String yearOfRelease;
    @NotEmpty
    private Set<TrackDTO> tracks = new HashSet<>();
    @NotEmpty
    private Set<GenreDTO> genres = new HashSet<>();
    @NotEmpty
    private Set<ArtistDTO> artists = new HashSet<>();

    public AlbumDTO() {
    }

    public Long getId() {
        return id;
    }

    public AlbumDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public AlbumDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public BandDTO getBand() {
        return band;
    }

    public AlbumDTO setBand(BandDTO band) {
        this.band = band;
        return this;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public AlbumDTO setYearOfRelease(String yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
        return this;
    }

    public Set<TrackDTO> getTracks() {
        return tracks;
    }

    public AlbumDTO setTracks(Set<TrackDTO> tracks) {
        this.tracks = tracks;
        return this;
    }

    public Set<GenreDTO> getGenres() {
        return genres;
    }

    public AlbumDTO setGenres(Set<GenreDTO> genres) {
        this.genres = genres;
        return this;
    }

    public Set<ArtistDTO> getArtists() {
        return artists;
    }

    public AlbumDTO setArtists(Set<ArtistDTO> artists) {
        this.artists = artists;
        return this;
    }
}
