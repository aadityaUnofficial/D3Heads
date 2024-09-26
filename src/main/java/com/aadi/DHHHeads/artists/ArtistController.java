package com.aadi.DHHHeads.artists;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private static final Logger log = LoggerFactory.getLogger(ArtistService.class);
    public ArtistService artService;

    public ArtistController(ArtistService artService){
        this.artService=artService;
    }

    /*
    boolean existsById(Integer id);

    void deleteByArtist_id(Integer artistId);

    Artist save(Artist artist);
     */

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    public List<Artists> findAll(){
        return artService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/name")
    public List<Artists> findAllByArtist_name(@RequestParam String artist_name){
        return artService.findAllByArtist_name(artist_name);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/label")
    public List<Artists> findAllByLabel(@RequestParam String label){
        return artService.findAllByLabel(label);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/genre")
    public List<Artists> findAllByGenre(@RequestParam String genre){
        return artService.findAllByGenre(genre);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public Artists save(@Valid @RequestBody Artists artists){
        return artService.save(artists);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/update")
    public Artists update(@Valid @RequestBody Artists artists){
        /*
        In case of update using ListCrudRepo RequestBody must have updated version, i.e. first find using artistId then take version and manually add
         */
        return artService.save(artists);
    }
    //Delete Remains

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete")
    public void delete(@RequestBody Artists artists){
        log.debug(artists+"");
        artService.deleteByArtist_id(artists);
    }
}
