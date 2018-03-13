package org.example.webservice.service;

import org.example.webservice.dto.BandDTO;

import java.util.Optional;
import java.util.Set;

public interface BandService {

    Set<BandDTO> findAll();

    BandDTO createBand(BandDTO bandDTO);

    Optional<BandDTO> findById(Long id);

    Optional<BandDTO> findByName(String name);

    BandDTO updateBand(BandDTO bandDTO);

    Boolean deleteBand(Long id);

    Boolean checkIfExist(BandDTO bandDTO);

}
