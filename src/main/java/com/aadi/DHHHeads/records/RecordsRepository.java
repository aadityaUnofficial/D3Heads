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
    23-09-2024 GetAll and Create Done
    24-09-2024 is GetByFields Update Delete
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

    public List<Record> getAllRecordsByTitle(String title){
        log.debug("Inside getAllRecordsByTitle " + title);
        return jdbcClient.sql("select * from records where LOWER(TRIM(title)) = LOWER(TRIM(:title))")
                .param("title",title)
                .query(Record.class)
                .list();

    }

    public List<Record> getAllRecordsByAlbum(String album){
        log.debug("Inside getAllRecordsByAlbum " + album);
        return jdbcClient.sql("select * from records where LOWER(TRIM(album)) = LOWER(TRIM(:album))")
                .param("album",album)
                .query(Record.class)
                .list();

    }

    public List<Record> getAllRecordsByArtist(String artist){
        log.debug("Inside getAllRecordsByAlbum " + artist);
        return jdbcClient.sql("select * from records where LOWER(TRIM(artist)) = LOWER(TRIM(:artist))")
                .param("artist",artist)
                .query(Record.class)
                .list();

    }

    public List<Record> getAllRecordsByAlbumId(Integer albumId){
        List<Record> recordsWithSameAlbumId = jdbcClient.sql("Select * from Records where album_id = :albumId")
                .param("albumId", albumId)
                .query(Record.class)
                .list();
        return recordsWithSameAlbumId;
    }

    public int updateRecords(Record record){
        //title, artist, album, producer lyricist
        log.debug("Update " + record);
        int rs = 0;
        try {
            rs = jdbcClient.sql("UPDATE records set album = ?, artist = ?, title = ?, producer = ? , lyricist = ?  where album_id = ? and track_num = ?")
                    .params(List.of(record.album(), record.artist(), record.title(), record.producer(), record.lyricist(), record.album_id(), record.track_num()))
                    .update();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        List<Record> allRecords = getAllRecords();
        log.debug(allRecords+"");
        log.debug("Update " + rs);
        return rs;
    }

    public void deleteRecord(Integer albumId, Integer trackNum){
        int rs = jdbcClient.sql("DELETE from records where album_id = ? and track_num = ?").params(List.of(albumId, trackNum)).update();
        log.debug("Deleted " + rs);

    }
}
