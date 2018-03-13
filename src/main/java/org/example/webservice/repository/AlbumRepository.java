package org.example.webservice.repository;

import org.example.webservice.entities.Album;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface AlbumRepository extends PagingAndSortingRepository<Album, Long> {

    Set<Album> findAll(Specification<Album> specification);

    Set<Album> findAll();

    Optional<Album> findOneById(Long id);


}
