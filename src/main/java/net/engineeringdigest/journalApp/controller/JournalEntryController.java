package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    public Map<Long, JournalEntry> journalEntries = new HashMap<>();


    //get
    //@GetMapping("/get-entries")  //-> route: /journal/get-entries
    @GetMapping                    //-> route: /journal
    public List<JournalEntry> getAl(){
        return new ArrayList<>(journalEntries.values());
    }

    //@PostMapping("/add-entries")//-> route: /journal/add-entries
    // we need data from user/client side so
    @PostMapping                  //-> route: /journal
    public void createEntry(@RequestBody JournalEntry myEntry){
        journalEntries.put(myEntry.getId(), myEntry);

    }

    @GetMapping("id/{myId}")//-> route journal/id/1
    public JournalEntry findById(@PathVariable Long myId){
        return journalEntries.get(myId);
    }


    @DeleteMapping("id/{myId}")
    public JournalEntry removeById(@PathVariable Long myId){
        return journalEntries.remove(myId);
    }

    @PutMapping("/id/{myId}")
    public JournalEntry updateById(@PathVariable Long myId, @RequestBody JournalEntry newEntry){
        return journalEntries.put(myId, newEntry);
    }





}
