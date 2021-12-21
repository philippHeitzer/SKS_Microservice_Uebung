package at.technikumwien.rewardservice.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlogIncoming {

    private Long id;

    private String title;

    private String blogText;

    private Author author;

    private Attraction attraction;

    public BlogIncoming(String title, String blogText, Author author, Attraction attraction)
    {
        this(null,title,blogText,author,attraction);
    }

    public BlogIncoming(String title, Author author, Attraction attraction)
    {
        this(null,title,null, author,attraction);
    }

}
