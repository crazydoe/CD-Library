package org.example.webservice.repository.specifications;

import org.example.webservice.entities.Album;
import org.springframework.data.jpa.domain.Specification;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import static org.springframework.data.jpa.domain.Specifications.where;


public class AlbumSpecificationBuilder {

    private Set<Specification<Album>> specificationSet = new HashSet<>();

    private static Specification<Album> titleCriteria(String title) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("title"), "%" + title + "%");
    }

    private static Specification<Album> bandCriteria(String bandName) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("band").get("name"), "%" + bandName + "%");
    }

    private static Specification<Album> genreCriteria(String genreName) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.join("genres").get("name"), "%" + genreName + "%");
    }

    private static Specification<Album> artistCriteria(String artistName) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.join("artists").get("name"), "%" + artistName + "%");
    }

    public AlbumSpecificationBuilder withArtist(String artistName) {
        if (artistName != null) {
            specificationSet.add(artistCriteria(artistName));
        }
        return this;
    }

    public AlbumSpecificationBuilder withBand(String bandName) {
        if (bandName != null) {
            specificationSet.add(bandCriteria(bandName));
        }
        return this;
    }

    public AlbumSpecificationBuilder withTitle(String title) {
        if (title != null) {
            specificationSet.add(titleCriteria(title));
        }
        return this;
    }

    public AlbumSpecificationBuilder withGenre(String genreName) {
        if (genreName != null) {
            specificationSet.add(genreCriteria(genreName));
        }
        return this;
    }

    public Optional<Specification<Album>> build() {
        if (specificationSet.isEmpty()) {
            return Optional.empty();
        }
        Iterator<Specification<Album>> iterator = specificationSet.iterator();
        Specification<Album> specification = iterator.next();
        while (iterator.hasNext()) {
            specification = where(specification).and(iterator.next());
        }
        return Optional.of(specification);
    }
}
