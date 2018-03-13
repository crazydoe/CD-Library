package org.example.webservice.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Band {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    public Long getId() {
        return id;
    }

    public Band setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Band setName(String name) {
        this.name = name;
        return this;
    }

}
