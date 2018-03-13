package org.example.webservice.web.api;

import org.example.webservice.dto.BandDTO;
import org.example.webservice.service.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "api/albums/")
public class BandController {

    private BandService bandService;
    @Autowired
    public BandController(BandService bandService) {
        this.bandService = bandService;
    }

    @RequestMapping(
            value = "/api/bands/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BandDTO> updateBandById(@RequestBody BandDTO band, @PathVariable Long id) {
        BandDTO updated = bandService.updateBand(band.setId(id));
        if(updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
    }
}
