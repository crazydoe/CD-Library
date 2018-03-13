package org.example.webservice.entities;

import javax.persistence.*;

@Entity
public class Track {
    @Id
    @GeneratedValue
    private Long id;
    private Long trackNumber;
    private String name;
    private Long duration;

    public Track() {
    }

    public Long getId() {
        return id;
    }

    public Long getTrackNumber() {
        return trackNumber;
    }

    public Track setTrackNumber(Long trackNumber) {
        this.trackNumber = trackNumber;
        return this;
    }

    public Track setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Track setName(String name) {
        this.name = name;
        return this;
    }

    public Long getDuration() {
        return duration;
    }

    public Track setDuration(Long duration) {
        this.duration = duration;
        return this;
    }
}
