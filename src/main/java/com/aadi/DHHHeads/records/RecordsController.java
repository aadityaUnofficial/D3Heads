package com.aadi.DHHHeads.records;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

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

}
