package at.technikumwien.blogservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="authors")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="firstname", nullable = true, length = 100)
    private String firstname;

    @Column(name="lastname" , nullable = false ,length = 100)
    private String lastname;

    public Author(String firstname,String lastname){
        this(null, firstname,lastname);
    }
}
