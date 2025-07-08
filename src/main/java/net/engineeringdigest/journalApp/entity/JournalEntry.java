package net.engineeringdigest.journalApp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
//@Getter
//@Setter
//@ToString

// or // use Lombok's @Data annotation which combines @Getter, @Setter, @ToString, @EqualsAndHashCode, and @RequiredArgsConstructor
@Data
public class JournalEntry {

    @Id//primary key
    private ObjectId id;
    private String title;
    private String content;

    private Date date;

    //no need for a constructor as Lombok's @Getter and @Setter will handle it
//    public Date getDate() {
//        return date;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//
//    public String getTitle() {
//        return title;
//    }

//    public void setTitle(String title) {
//        this.title = title;
//    }

    public Object getId() {
        return id;
    }

//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    public void setId(ObjectId id) {
//        this.id = id;
//    }
}
