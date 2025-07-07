package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;


    @GetMapping
    public List<JournalEntry> getAll(){
        return  journalEntryService.getAll();
    }

    @PostMapping
    public JournalEntryService createEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDate(new Date());
        journalEntryService.saveJournalEntry(myEntry);
        return journalEntryService;

    }

    @GetMapping("id/{myId}")//-> route journal/id/1
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myId){
        return journalEntryService.findById(myId).orElse(null);
    }


    @DeleteMapping("id/{myId}")
    public boolean removeById(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
        return true;
    }


    @PutMapping("/id/{myId}")
    public JournalEntry updateById(@PathVariable ObjectId myId,@RequestBody JournalEntry newEntry){
        //find the entry
        JournalEntry existingEntry = journalEntryService.findById(myId).orElse(null);

        if(existingEntry != null){
            existingEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : existingEntry.getTitle() );
            existingEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : existingEntry.getContent() );
        }

        journalEntryService.saveJournalEntry(existingEntry);
        return existingEntry;
    }

//controller --> service --> repository



}
