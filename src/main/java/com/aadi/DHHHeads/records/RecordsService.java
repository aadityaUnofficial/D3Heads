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

    /*private void setDefaultValueToFields(Record record) {
        log.debug("Entering setDefaults input is ", record);
        if(record.album() == null){
            record.album("UKWN");
        }
    }*/
}
