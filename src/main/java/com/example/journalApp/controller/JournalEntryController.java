package com.example.journalApp.controller;


import com.example.journalApp.entity.JournalEntry;
import com.example.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;
    @GetMapping
    public List<JournalEntry> getAll() {
        return journalEntryService.getAll();
    }

    @PostMapping
    public JournalEntry createEntery(@RequestBody JournalEntry myentry) {
        myentry.setData(LocalDateTime.now());
        journalEntryService.saveEntry(myentry);
        return myentry;
    }

    @GetMapping("id/{id}")
    public JournalEntry getJournalEntrybyId(@PathVariable ObjectId id) {
        return journalEntryService.findbyId(id).orElse(null);
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteJournalEntrybyId(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
        return true;
    }

    @PutMapping("id/{myId}")
    public JournalEntry updateJournalEntrybyId(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry){
        JournalEntry old=journalEntryService.findbyId(myId).orElse(null);
        if (old != null){
            old.setTitle(newEntry.getTitle() !=null && !newEntry.getTitle().equals("") ? newEntry.getTitle(): old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() :
                    old.getContent());
        }
        journalEntryService.saveEntry(old);
        return old;


    }
}
