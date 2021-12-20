package at.technikumwien.blogservice.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="blogs")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title" , length = 100, nullable = false)
    private String title;

    @Column(name = "blog_text", length = 16000, nullable = false)
    private String blogText;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "authorid")
    private Author author;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "attractionid")
    private Attraction attraction;

    public Blog(String title, String blogText, Author author,Attraction attraction)
    {
        this(null,title,blogText,author,attraction);
    }

}
