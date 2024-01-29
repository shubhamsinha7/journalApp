package com.example.journalApp.controller;


import com.example.journalApp.entity.JournalEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    private Map<Long, JournalEntity> journalEntities=new HashMap<>();
    @GetMapping
    public List<JournalEntity> getAll(){
        return new ArrayList<>(journalEntities.values());
    }
    @PostMapping
    public boolean createEntery(@RequestBody JournalEntity objentry){
        journalEntities.put(objentry.getId(),objentry);
        return true;
    }
    @GetMapping("id/{id}")
    public JournalEntity getJournalEntitybyId(@PathVariable Long id){
        return journalEntities.get(id);
    }
}
