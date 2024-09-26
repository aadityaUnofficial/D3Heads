package com.aadi.DHHHeads.artists;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ArtistRepository extends ListCrudRepository<Artists, Integer> {
    List<Artists> findAllByGenre(String genre);

    List<Artists> findAllByArtistName(String artist_name);

    List<Artists> findAllByLabel(String label);

    List<Artists> findAll();

    Artists findByArtistId(Integer id);

    boolean existsById(Integer id);

    void deleteByArtistId(Integer artistid);

}
