package net.engineeringdigest.journalApp.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
public class JournalEntry {

    @Id//primary key
    private ObjectId id;
    private String title;
    private String content;

    private Date date;

    public Date getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getId() {
        return id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
