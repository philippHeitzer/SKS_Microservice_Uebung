package at.technikumwien.rewardservice.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Blog {

    private Long id;

    private String title;

    private String blogText;

    private Author author;

    public Blog(String title, String blogText, Author author)
    {
        this(null,title,blogText,author);
    }

    public Blog(String title, Author author)
    {
        this(null,title,null, author);
    }

}
