package com.aadi.DHHHeads.artists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    private static final Logger log = LoggerFactory.getLogger(ArtistService.class);
    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository){
        this.artistRepository=artistRepository;
    }


    public List<Artists> findAllByGenre(String genre){
        log.debug("Find by Genre " + genre);
        return artistRepository.findAllByGenre(genre);
    }

    public List<Artists> findAllByArtist_name(String artist_name){
        log.debug("Find by artistName " + artist_name);
        return artistRepository.findAllByArtistName(artist_name);
    }

    public List<Artists> findAllByLabel(String label){
        log.debug("Find by label " + label);
        return artistRepository.findAllByLabel(label);
    }

    public List<Artists> findAll(){
        log.debug("Find  ");
        return artistRepository.findAll();
    }

    void deleteByArtist_id(Artists artists){
        log.debug("Here Delete " + artistRepository.existsById(artists.artistId()));
        artistRepository.delete(artists);
        log.debug("After Delete " + artistRepository.existsById(artists.artistId()));
    }

    Artists save(Artists artists){
        Artists byArtistId = artistRepository.findByArtistId(artists.artistId());
        log.debug("Here Save " + artists);
        Artists save = artistRepository.save(artists);
        log.debug("After Save " + artistRepository.existsById(artists.artistId()));
        return save;
    }
}
