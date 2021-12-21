package at.technikumwien.rewardservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="blog_calls")
@NoArgsConstructor
@AllArgsConstructor
@Data

@NamedEntityGraph(
        name = "blog_calls.fetchAuthors",
        attributeNodes = {
                @NamedAttributeNode("author")
        }
)
public class BlogCalls {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "authorid")
    Author author;

    @Column(name = "calls" ,columnDefinition = "integer default 0")
    Long calls;

    public BlogCalls(Author author, Long calls)
    {
        this(null,author,calls);
    }
}
