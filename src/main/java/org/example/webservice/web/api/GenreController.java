package org.example.webservice.web.api;

import org.example.webservice.dto.GenreDTO;
import org.example.webservice.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class GenreController {

    private GenreService service;
    @Autowired
    public GenreController(GenreService service) {
        this.service = service;
    }

    @RequestMapping(
            value = "/api/genres/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Set<GenreDTO>>getGenres(){
        Set<GenreDTO> genres = service.findAll();
        if(genres == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(genres, HttpStatus.OK);
        }
    }

    @RequestMapping(
            value = "/api/genres/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GenreDTO> getGenreById(@PathVariable Long id ){
        GenreDTO genre = service.findById(id);
        if(genre == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(genre, HttpStatus.OK);
        }
    }

    @RequestMapping(
            value = "/api/genres/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GenreDTO>updateGenreById(@RequestBody GenreDTO genre, @PathVariable Long id){
        GenreDTO updated = service.updateGenre(genre.setId(id));
        if(updated == null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
    }

    @RequestMapping(
            value = "/api/genres/{id}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity deleteGenreById(@PathVariable Long id){
        if(service.deleteGenre(id)){
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
