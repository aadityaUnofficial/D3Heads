package com.aadi.DHHHeads.records;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecordsRepository {

    private static final Logger log = LoggerFactory.getLogger(RecordsRepository.class);
    private final JdbcClient jdbcClient;

    public RecordsRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    /*
    Start with normal CRUD 23-09-2024
     */

    public void createNewRecord(Record record){
        log.debug("Create New " , record);
        System.out.println(record);
        jdbcClient.sql("INSERT INTO records(title,artist,album,release_date,duration,track_num,label,album_id,producer,lyricist,cover_art,all_credits,language,explicit_content) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)")
                .params(List.of( record.title(), record.artist(), record.album(), record.release_date(), record.duration(), record.track_num(),
                                record.label(), record.album_id(), record.producer(), record.lyricist(), record.cover_art(), record.all_credits(), record.language(), record.explicit_content()))
                .update();

    }

    public List<Record> getAllRecords(){
        log.debug("In getAllRecords");
        return jdbcClient.sql("select * from records")
                .query(Record.class)
                .list();
    }
}
