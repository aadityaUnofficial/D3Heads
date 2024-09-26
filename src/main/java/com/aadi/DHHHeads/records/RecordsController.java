package com.aadi.DHHHeads.records;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/records")
public class RecordsController {

    private static final Logger log = LoggerFactory.getLogger(RecordsController.class);
    private RecordsService recordsService;

    public RecordsController(RecordsService recordsService){
        this.recordsService=recordsService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public void createRecord(@Valid @RequestBody Record record){
        recordsService.createNewRecord(record);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    public List<Record> getAllRecords(){
        return recordsService.getAllRecords();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{title}")
    public List<Record> getAllRecordsByTitle(@PathVariable String title){
        List<Record> rcrds = recordsService.getAllRecordsByTitle(title);
        if(rcrds.size()==0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No Record Found");
        }
        return rcrds;
    }

    /*
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public List<Record> getAllRecordsByAlbum(@RequestParam String album, @RequestParam(required = false) String artist){
        log.debug("Album " + album + " Artist " + artist);
        List<Record> allRecordsByAlbum = recordsService.getAllRecordsByAlbum(album);
        if(allRecordsByAlbum.size()==0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        else return allRecordsByAlbum;
    }*/

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/albums/{album}")
    public List<Record> getAllRecordsByAlbum(@PathVariable String album){
        log.debug("Album " + album);
        List<Record> allRecordsByAlbum = recordsService.getAllRecordsByAlbum(album);
        if(allRecordsByAlbum.size()==0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        else return allRecordsByAlbum;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/artists/{artist}")
    public List<Record> getAllRecordsByArtist(@PathVariable String artist){
        //artist=artist.replaceAll(" ","");
        List<Record> allRecordsByArtist = recordsService.getAllRecordsByArtist(artist);

        if(allRecordsByArtist.size()==0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return allRecordsByArtist;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/update")
    public int updateRecord(@RequestBody Record record){
        log.debug("updt record "+record);
        return recordsService.updateRecord(record);
    }

    @DeleteMapping("/delete")
    public void deleteRecord(@RequestParam Integer albumId, @RequestParam Integer trackNum){
        log.debug("Delete " + albumId + " " + trackNum);
        recordsService.deleteRecord(albumId,trackNum);
    }

}
