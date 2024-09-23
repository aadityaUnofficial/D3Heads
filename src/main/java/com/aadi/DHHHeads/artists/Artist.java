package com.aadi.DHHHeads.artists;

import java.sql.Date;

public record Artist(
        Integer artist_id,
        String artist_name,
        String genre,
        String bio,
        String image,
        Date dob,
        String pob,
        String url,
        String socials,
        String label,
        String awards,
        String influences
) {
}
