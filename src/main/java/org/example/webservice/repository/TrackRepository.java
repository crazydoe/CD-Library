package org.example.webservice.repository;

import org.example.webservice.entities.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {

}
