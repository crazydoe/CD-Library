package org.example.webservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.webservice.entities.Album;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class TrackDTO {

    private Long id;
    @NotNull
    private Long trackNumber;
    @NotBlank
    private String name;
    @NotNull
    private Long duration;

    public Long getId() {
        return id;
    }

    public TrackDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getTrackNumber() {
        return trackNumber;
    }

    public TrackDTO setTrackNumber(Long trackNumber) {
        this.trackNumber = trackNumber;
        return this;
    }

    public String getName() {
        return name;
    }

    public TrackDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Long getDuration() {
        return duration;
    }

    public TrackDTO setDuration(Long duration) {
        this.duration = duration;
        return this;
    }
}