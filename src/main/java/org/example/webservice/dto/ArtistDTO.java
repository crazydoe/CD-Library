package org.example.webservice.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.webservice.entities.Album;
import org.hibernate.validator.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

public class ArtistDTO {

    private Long id;
    @NotBlank
    private String name;

    public Long getId() {
        return id;
    }

    public ArtistDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ArtistDTO setName(String name) {
        this.name = name;
        return this;
    }
}
