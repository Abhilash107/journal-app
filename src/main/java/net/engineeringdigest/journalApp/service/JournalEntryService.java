package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    //here service ---> repository(mongoDB repository, an interface)
    @Transactional
    public void saveJournalEntry(JournalEntry journalEntry, String userName){
        try {
            User findUSer = userService.findByUserName(userName);
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            findUSer.getJournalEntries().add(saved);
            //findUSer.setUserName(null);
            userService.saveEntry(findUSer);
        }catch (Exception e){
            System.out.printf(String.valueOf(e));
            throw new RuntimeException("An error has occurred", e);

        }

   }

    //overloaded method for one arg
    public void saveJournalEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName){
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }

}
