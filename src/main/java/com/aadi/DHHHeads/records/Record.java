package com.aadi.DHHHeads.records;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

import java.sql.Date;
import java.sql.Time;


public record Record(
        Integer record_id,
        @NotNull String title,
        String artist,
        String album,
        Date release_date,
        Time duration,
        @Id Integer track_num,
        String label,
        Integer album_id,
        String producer,
        String lyricist,
        String cover_art,
        String all_credits,
        String language,
        String explicit_content
) {
}
