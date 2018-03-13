package org.example.webservice.entities;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Album {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "album_band",
            joinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "band_id", referencedColumnName = "id")
    )
    private Band band;

    private String yearOfRelease;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "album_track",
            joinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "track_id", referencedColumnName = "id"))
    private Set<Track> tracks = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "album_genre",
            joinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
    private Set<Genre> genres = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "album_artist",
            joinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "id"))
    private Set<Artist> artists = new HashSet<>();

    public Set<Artist> getArtists() {
        return artists;
    }

    public Album setArtists(Set<Artist> artists) {
        this.artists = artists;
        return this;
    }

    public Band getBand() {
        return band;
    }

    public Album setBand(Band band) {
        this.band = band;
        return this;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public Album setYearOfRelease(String yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Album setId(Long id) {
        this.id = id;
        return this;
    }

    public Set<Track> getTracks() {
        return tracks;
    }

    public Album setTracks(Set<Track> tracks) {
        this.tracks = tracks;
        return this;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public Album setGenres(Set<Genre> genres) {
        this.genres = genres;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Album setTitle(String albumName) {
        this.title = albumName;
        return this;
    }

    public void addArtist(Artist artist) {
        this.artists.add(artist);
    }

    public void addGenre(Genre genre) {
        this.genres.add(genre);
    }
}