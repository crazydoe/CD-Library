package org.example.webservice.repository;

import org.example.webservice.entities.Band;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface BandRepository extends PagingAndSortingRepository<Band, Long> {

    Optional<Band> findOneByName(String name);

    Set<Band> findAll();
}
