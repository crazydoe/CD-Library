package org.example.webservice.web.api;

import org.example.webservice.dto.ArtistDTO;
import org.example.webservice.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/albums/{id}/artist")
public class ArtistController {

    private ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

//    @RequestMapping(
//            method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE
//    )
//    public ResponseEntity<ArtistDTO> postOne(@PathVariable("id") Long albumId, @RequestBody ArtistDTO newArtist){
//        Optional<ArtistDTO> artist = artistService.createForAlbum(newArtist, albumId);
//        return artist.map(artistDTO -> new ResponseEntity<>(artistDTO, HttpStatus.CREATED))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE));
//    }
}
