package at.technikumwien.blogservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="attractions")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Attraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false, length = 100)
    private String name;

    @Column(name="description" , nullable = true, length = 4096)
    private String description;

    public Attraction(String name,String description)
    {
        this(null,name, description);
    }
}
