package org.example.webservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.webservice.entities.Album;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Set;


public class BandDTO {

    private Long id;
    @NotBlank
    private String name;

    public Long getId() {
        return id;
    }

    public BandDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BandDTO setName(String name) {
        this.name = name;
        return this;
    }
}
