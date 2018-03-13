package org.example.webservice.web.api;

import org.example.webservice.dto.AlbumDTO;
import org.example.webservice.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/albums")
public class AlbumController {

    private AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Set<AlbumDTO>> getAlbums(@RequestParam(value = "artist", required = false) String artist,
                                                   @RequestParam(value = "genre", required = false) String genre,
                                                   @RequestParam(value = "band", required = false) String band,
                                                   @RequestParam(value = "title", required = false) String title) {
        Set<AlbumDTO> response = albumService.find(artist, band,genre, title);
        if(response.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AlbumDTO> createAlbum(@RequestBody AlbumDTO album) {
        Optional<AlbumDTO> newAlbum = albumService.create(album);
        return newAlbum.map(albumDTO -> new ResponseEntity<>(albumDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AlbumDTO> getAlbum(@PathVariable("id") Long id) {
        Optional<AlbumDTO> album = albumService.findOne(id);
        return album.map(albumDTO -> new ResponseEntity<>(albumDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AlbumDTO> updateAlbum(@PathVariable("id") Long id, @RequestBody AlbumDTO album) {
        if(!id.equals(album.getId()) || !albumService.exist(album.getId())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<AlbumDTO> updated = albumService.update(album);
        return updated.map(albumDTO -> new ResponseEntity<>(albumDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity deleteAlbum(@PathVariable("id") Long id) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
