package org.example.webservice.repository;

import org.example.webservice.entities.Artist;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ArtistRepository extends PagingAndSortingRepository<Artist, Long>, JpaSpecificationExecutor {

    Optional<Artist> findOneByName(String name);

    Set<Artist> findAll();

}
