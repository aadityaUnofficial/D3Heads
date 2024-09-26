package com.aadi.DHHHeads.records;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordsService {

    private static final Logger log = LoggerFactory.getLogger(RecordsService.class);
    private RecordsRepository repo;

    public RecordsService(RecordsRepository repo){
        this.repo=repo;
    }

    void createNewRecord(Record record){
        //setDefaultValueToFields(record);
        log.debug("Create New Record "+ record);
        repo.createNewRecord(record);
    }

    public List<Record> getAllRecords(){
        return repo.getAllRecords();
    }

    public List<Record> getAllRecordsByTitle(String title){
        return repo.getAllRecordsByTitle(title);
    }

    public List<Record> getAllRecordsByAlbum(String album){
        return repo.getAllRecordsByAlbum(album);
    }

    public List<Record> getAllRecordsByArtist(String artist ){
        return repo.getAllRecordsByArtist(artist);
    }

    public List<Record> getAllRecordsByAlbum_RecordId(Integer albumId, Integer trackNum){
        if(albumId == -1 || trackNum == -1){
            log.debug("Album Or Record ID are required");
        }

        List<Record> allRecordsByAlbumId = repo.getAllRecordsByAlbumId(albumId);

        log.debug("All Albums by Album Id  " + albumId);
        log.debug(allRecordsByAlbumId.toString());
        List<Record> allRecordsByAlbum_RecordId = allRecordsByAlbumId.stream().filter(record -> record.track_num() == trackNum).toList();
        log.debug("All Records by Records Id  " + trackNum);
        log.debug(allRecordsByAlbum_RecordId.toString());

        return allRecordsByAlbum_RecordId;

    }

    public int updateRecord(Record record){
        log.debug("updateRecord " + record);
        List<Record> allRecordsByAlbumRecordId = getAllRecordsByAlbum_RecordId(record.album_id(), record.track_num());
        if(allRecordsByAlbumRecordId.size()==0){
            log.debug("No Record for this Album and Record Id Combo");
            return -1;
        }
        //title, artist, album, producer lyricist
        return repo.updateRecords(record);

    }

    public void deleteRecord(Integer albumId, Integer trackNum){
        repo.deleteRecord(albumId,trackNum);
    }
    /*private void setDefaultValueToFields(Record record) {
        log.debug("Entering setDefaults input is ", record);
        if(record.album() == null){
            record.album("UKWN");
        }
    }*/
}
