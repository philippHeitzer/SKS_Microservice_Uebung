package at.technikumwien.rewardservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="blogs")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class BlogCalls {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "callNumber" ,columnDefinition = "integer default 0")
    Long calls;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "authorid")
    Author author;


    public BlogCalls(Author author, Long calls)
    {
        this(null,calls, author);
    }
}
