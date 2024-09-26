package com.aadi.DHHHeads.artists;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.sql.Date;

public record Artists(
        @Id Integer artistId,
        @NotNull String artistName,
        @NotNull String genre,
        String bio,
        String image,
        Date dob,
        String pob,
        String url,
        String socials,
        String label,
        String awards,
        String influences,
        @Version Integer version
) {
}
