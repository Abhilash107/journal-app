package net.engineeringdigest.journalApp.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
public class User {

    @Id//(mapped)
    private ObjectId id;//string can be used

    @Indexed(unique = true)//(unique userNames)(doesn't create unique username automatically)
    @NonNull()//Not null
    private String userName;

    @NonNull()////Not null
    private String password;

    @DBRef // MongoDB reference to JournalEntry documents (similar to ObjectId reference in Node.js)
    private List<JournalEntry> journalEntries = new ArrayList<>();


}
