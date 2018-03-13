package org.example.webservice.repository;

import org.example.webservice.entities.Genre;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface GenreRepository extends PagingAndSortingRepository<Genre, Long> {

    Optional<Genre> findOneByName(String name);

    Set<Genre> findAll();

    Optional<Genre> findOneById(Long id);
}
