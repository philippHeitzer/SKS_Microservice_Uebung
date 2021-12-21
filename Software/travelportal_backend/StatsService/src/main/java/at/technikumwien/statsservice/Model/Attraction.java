package at.technikumwien.statsservice.Model;

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

    @Column(name="count_this_month" , columnDefinition = "integer default 0")
    private Integer countThisMonth;

    @Column(name="count_all_time", columnDefinition = "integer default 0"  )
    private Integer countAllTime;

    public Attraction(String name)
    {
        this(null, name,0,0);
    }

    public Attraction(String name,Integer countThisMonth, Integer countAllTime)
    {
        this(null,name, countThisMonth,countAllTime);
    }
}
