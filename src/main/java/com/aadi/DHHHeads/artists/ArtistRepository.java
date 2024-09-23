package com.aadi.DHHHeads.artists;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ArtistRepository extends ListCrudRepository<Artist, Integer> {
    List<Artist> findAllByGenre(String genre);

}
